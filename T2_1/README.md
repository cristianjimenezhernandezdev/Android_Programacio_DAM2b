# T2_1 – Fragments + BottomNavigation (DAM2)

Projecte Android fet amb a treball de classe. L’objectiu és navegar entre diferents fragments utilitzant un **BottomNavigationView**.
Projecte Github:
https://github.com/cristianjimenezhernandezdev/Android_Programacio_DAM2b/tree/master/T2_1

## Objectiu del projecte

- Practicar l’ús de **Fragments**.
- Implementar un **menú de navegació inferior** amb `BottomNavigationView`.
- Aplicar una **millora visual** (colors, tema, indicador seleccionat) perquè la UI sigui més agradable.

## Funcionament (què fa l’app)

1. La pantalla principal carrega un `FrameLayout` que fa de **contenidor de fragments**.
2. A la part inferior hi ha un `BottomNavigationView` amb 3 opcions:
   - *Primer Fragment*
   - *Segon Fragment*
   - *Tercer Fragment*
3. En prémer un ítem del menú, es reemplaça el fragment que hi ha dins del contenidor.

> Nota: el menú està definit a `app/src/main/res/menu/navigation_menu.xml`.

## Resum de canvis (millora d’interfície)

Per millorar visualment, He afegit estils (fent us de IA):

---

## Fitxers creats (Estils IA)

### 1) `app/src/main/res/color/nav_item_color.xml`
** Un fitxer *selector* de colors.

**Ús**
Defineix el color de les icones i del text del `BottomNavigationView` depenent de l’estat:
- **Seleccionat** (`state_checked=true`) → `@color/primary`
- **No seleccionat** → `@color/secondary`


---

### 2) `app/src/main/res/values-night/colors.xml`
** Fitxer de colors per a **modo escuro** (`values-night`).

**Ús**
Quan el movil està en mode oscuro, Android carga automàticament aquests valors en comptes dels de `values/colors.xml`.


---

## Fitxers modificats (important)

### 1) `app/src/main/res/layout/activity_main.xml`
**Canvis**
- S’ha afegit `android:background="@color/background"` al contenidor principal.
- S’ha afegit `padding` al `FrameLayout` del contenidor de fragments.
- S’ha millorat el `BottomNavigationView`:
  - `android:background="@color/nav_background"`
  - `android:elevation="8dp"`
  - `app:itemIconTint="@color/nav_item_color"`
  - `app:itemTextColor="@color/nav_item_color"`
  - `app:itemActiveIndicatorStyle="@style/NavigationIndicatorStyle"`

---

### 2) `app/src/main/res/values/colors.xml`
**Canvis**
S'ha ampliat la paleta de colors:
- `primary`, `secondary`, `background`, etc.
- colors específics per la navegació (`nav_background`, `nav_indicator`).

---

### 3) `app/src/main/res/values/themes.xml`
**Canvis**
- S’han definit colors del tema:
  - `colorPrimary`, `colorSecondary`, `android:colorBackground`, etc.
- S’ha creat l’estil `NavigationIndicatorStyle` per donar color a l’indicador actiu del `BottomNavigationView`.

---

### 4) `app/src/main/res/values-night/themes.xml`
**Canvis**
S’ha actualitzat el tema per mode fosc perquè utilitzi els colors de `values-night/colors.xml`.

---


## Recursos relacionats

- Menú del bottom navigation: `app/src/main/res/menu/navigation_menu.xml`
- Aquest boto és important perque s'encarrega de donar format al menú que hem creat per navegar entre fragments.

---


## Com es defineixen i es trasllada la informació dels bundles

Després de seguir el tutorial de fragments + menú, l’exercici demana **passar informació a cada Fragment mitjançant un `Bundle`** i que cada fragment la mostri com un mini currículum:

- **Primer Fragment:** nom, cognoms, data de naixement i lloc de naixement.
- **Segon Fragment:** experiència acadèmica (títol, entitat i anys).
- **Tercer Fragment:** experiència laboral (tipus de feina i període).

### Com està fet

#### 1) Creació del `Bundle` a `MainActivity`
A `MainActivity` (listener del `BottomNavigationView`) es crea un `Bundle` amb les dades que volem mostrar i es passa al fragment quan fem el `replace(...)`.

En el meu cas utilitzo `bundleOf(...)` (de `androidx.core.os`) perquè és més curt que crear un `Bundle()` manualment.

**Dades:**

- **Primer Fragment**:
  - Claus/valors: `"Nom"`, `"Cognoms"`, `"DataNaixement"`, `"LlocNaixement"` i `"Edat"`.

- **Segon Fragment** (llista d’estudis):
  - Es passa una `ArrayList<String>` amb la clau `"Estudis"`.

- **Tercer Fragment** (llista de feines):
  - Es passa una `ArrayList<String>` amb la clau `"Feines"`.

Quan canvio de pantalla amb el menú, faig:
- `supportFragmentManager.commit { replace<FragmentX>(..., args = bundle) }`

I al primer inici de l’app carrego el fragment 1 amb `add<PrimerFragment>(..., args = bundle)`.

#### 2) Recepció del `Bundle` dins de cada Fragment
Dins de cada fragment, Android guarda el `Bundle` a `arguments`.

A `onViewCreated(...)` recupero les dades:

- **PrimerFragment**
  - `arguments?.getString("Nom")`, `getString("Cognoms")`, etc.
  - faig `.orEmpty()` per si passa un null que no peti (control d'errors).

- **SegonFragment**
  - `arguments?.getStringArrayList("Estudis") ?: arrayListOf()`
  - Si la llista està buida, mostro `(Sense dades)`.

- **TercerFragment**
  - `arguments?.getStringArrayList("Feines") ?: arrayListOf()`
  - Igual que l’anterior: si no hi ha dades, mostro `(Sense dades)`.

#### 3) Mostrar la informació a la UI
Per simplificar, cada fragment mostra el text en un `TextView` (`R.id.fragmentText`).

Genero un text amb `buildString { ... }` i el poso així:
- `view.findViewById<TextView>(R.id.fragmentText).text = text`

---

## Millora estètica dels Fragments


### Què s’ha fet

1) **Fons suau a tota la pantalla del fragment**
- He afegit un fons reutilitzable amb un `shape` que fa servir `@color/background` (així respecta mode clar/fosc).
- Fitxer creat:
  - `app/src/main/res/drawable/bg_fragment_screen.xml`

2) **Targeta (card) amb cantonades rodones i padding**
- He creat un altre `shape` que actua com a “targeta” (`surface`, `radius` i `padding`).
- Fitxer creat:
  - `app/src/main/res/drawable/bg_fragment_card.xml`

3) **Layouts dels fragments actualitzats**
- Abans: un `LinearLayout` amb un `TextView` ocupant tota la pantalla.
- Ara amb els canvis: un `FrameLayout` amb el fons i, al mig, un `LinearLayout` amb la card.
- Atributs visuals:
  - `android:layout_margin="16dp"`
  - `android:background="@drawable/bg_fragment_card"`
  - `android:elevation="2dp"` una sombra
  - `android:textSize="16sp"` i `android:lineSpacingExtra="4dp"`
  - `android:textColor="@color/on_surface"`

Fitxers modificats:
- `app/src/main/res/layout/fragment_primer.xml`
- `app/src/main/res/layout/fragment_segon.xml`
- `app/src/main/res/layout/fragment_tercer.xml`
