package campalans.m8.camerajc

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import campalans.m8.camerajc.ui.theme.CameraJCTheme
import android.Manifest
import coil3.compose.rememberAsyncImagePainter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainUi()
        }
    }

    @Composable
    fun MainUi() {

        val context = LocalContext.current
        var imageUri by remember { mutableStateOf<Uri?>(null) }//Lu més comú és una url es una variable que guarda la url
        var lastModified by remember { mutableStateOf(System.currentTimeMillis()) }

        // CAMERA RESULT
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                imageUri?.let { uri ->
                    // Force update so the image becomes readable
                    val values = ContentValues().apply {
                        put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                    }
                    context.contentResolver.update(uri, values, null, null)

                    // Force recomposition → refresh painter cache
                    lastModified = System.currentTimeMillis()
                }

            } else {
                Toast.makeText(context, "Cancelled!", Toast.LENGTH_SHORT).show()
            }
        }

        // Permissions
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val allGranted = permissions.values.all { it }

            if (allGranted) {
                pickImageFromCamera(context) { uri ->
                    imageUri = uri
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    cameraLauncher.launch(intent)
                }
            } else {
                Toast.makeText(context, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            imageUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(
                        model = "$uri?t=$lastModified" // force reload cache
                    ),
                    contentDescription = "Captured Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                val requiredPermissions =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                        arrayOf(Manifest.permission.CAMERA)
                    else
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )

                permissionLauncher.launch(requiredPermissions)
            }) {
                Text("Capture Image")
            }
        }
    }

    fun pickImageFromCamera(
        context: android.content.Context,
        onImageUriCreated: (Uri) -> Unit
    ) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "TEMP_IMAGE")
            put(MediaStore.Images.Media.DESCRIPTION, "Captured by Camera")
        }

        val uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        onImageUriCreated(uri!!)
    }

    /**
     * GreetingPreview is a composable function for previewing the MainUI in Android Studio.
     * It is annotated with @Preview to enable live preview.
     *
     */
    @Preview(showBackground = true)
    @Composable
    private fun GreetingPreview() {

        MainUi()

    }
}