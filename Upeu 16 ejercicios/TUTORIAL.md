# 📖 TUTORIAL: EJERCICIOS INTERACTIVOS

## ✨ Versiones Interactivas Disponibles

Cada ejercicio con ArrayList ahora tiene una versión interactiva donde puedes **agregar datos manualmente**:

---

## 🎯 EJERCICIOS INTERACTIVOS

### **FÁCIL**
- `TiendaInteractiva.java` - Gestión de productos en tiempo real

### **MEDIO**
- `BibliotecaInteractiva.java` - Gestión de libros
- `NominaInteractiva.java` - Gestión de empleados
- `RankingInteractivo.java` - Gestión de jugadores

### **DIFÍCIL**
- `ReservasInteractiva.java` - Sistema de reservas
- `VehiculosInteractiva.java` - Fábrica de vehículos

### **EXTRA**
- `HospitalInteractivo.java` - Sistema hospitalario completo

---

## 🚀 CÓMO COMPILAR Y EJECUTAR

### Opción 1: Compilar TODO

```bash
cd "c:\Users\USUARIO\Documents\upeuclasessistemassss\Uperu 16 ejerciciosde de clase"

# Compilar todo (FACIL, MEDIO, DIFICIL, EXTRA)
javac FACIL/*.java MEDIO/*.java DIFICIL/*.java EXTRA/*.java
```

### Opción 2: Compilar por carpeta

```bash
# FACIL
cd FACIL
javac *.java
java TiendaInteractiva

# MEDIO
cd ..\MEDIO
javac *.java
java BibliotecaInteractiva
# o java NominaInteractiva
# o java RankingInteractivo

# DIFICIL
cd ..\DIFICIL
javac *.java
java ReservasInteractiva
# o java VehiculosInteractiva

# EXTRA
cd ..\EXTRA
javac *.java
java HospitalInteractivo
```

---

## 📋 GUÍA DE USO POR EJERCICIO

### 1️⃣ TiendaInteractiva

```
╔═══════════════════════════════╗
║   TIENDA INTERACTIVA            ║
╚═══════════════════════════════╝
1. Agregar producto
2. Ver todos los productos
3. Buscar por categoría
4. Eliminar producto
5. Cargar datos de prueba
0. Salir

Selecciona opción: 1
Nombre: Laptop
Precio: 800
Stock: 5
Categoría: Electrónica
✓ Producto agregado
```

---

### 2️⃣ BibliotecaInteractiva

```
╔═══════════════════════════════╗
║   BIBLIOTECA INTERACTIVA        ║
╚═══════════════════════════════╝
1. Agregar libro
2. Ver todos los libros
3. Buscar por autor
4. Libro más caro
5. Eliminar libro
6. Cargar datos de prueba
0. Salir

Selecciona opción: 1
Título: Clean Code
Autor: Robert Martin
ISBN: ISBN001
Precio: 50
✓ Libro agregado
```

---

### 3️⃣ NominaInteractiva

```
╔═══════════════════════════════╗
║   NÓMINA DE EMPLEADOS           ║
╚═══════════════════════════════╝
1. Agregar empleado fijo
2. Agregar empleado temporal
3. Ver nómina completa
4. Total de nómina
5. Cargar datos de prueba
0. Salir

Selecciona opción: 1
Nombre: Juan
ID: EF001
Salario mensual: 3000
✓ Empleado fijo agregado
```

---

### 4️⃣ RankingInteractivo

```
╔═══════════════════════════════╗
║   RANKING DE JUGADORES          ║
╚═══════════════════════════════╝
1. Agregar jugador
2. Ver ranking completo
3. Ver top N jugadores
4. Puntos totales
5. Cargar datos de prueba
0. Salir

Selecciona opción: 1
Nombre del jugador: Player1
Puntos: 150
✓ Jugador agregado
```

---

### 5️⃣ ReservasInteractiva

```
╔═══════════════════════════════╗
║   CENTRAL DE RESERVAS           ║
╚═══════════════════════════════╝
1. Nueva reserva
2. Ver todas las reservas
3. Confirmar reserva
4. Cancelar reserva
5. Buscar por cliente
6. Reservas del día
7. Cargar datos de prueba
0. Salir

Selecciona opción: 1
Cliente: Juan
Fecha (YYYY-MM-DD): 2024-01-15
Habitación: 101
✓ Reserva creada con ID: 1
```

---

### 6️⃣ VehiculosInteractiva

```
╔═══════════════════════════════╗
║   FÁBRICA DE VEHÍCULOS          ║
╚═══════════════════════════════╝
1. Crear Auto
2. Crear Moto
3. Crear Camión
4. Ver todos los vehículos
5. Cargar datos de prueba
0. Salir

Selecciona opción: 1
Marca: Toyota
Número de puertas: 4
✓ Auto agregado
```

---

### 7️⃣ HospitalInteractivo

```
╔════════════════════════════════════╗
║   SISTEMA DEL HOSPITAL              ║
╚════════════════════════════════════╝
1. Registrar nuevo doctor
2. Registrar nuevo paciente
3. Asignar paciente a doctor
4. Ver historial de paciente
5. Reporte general
6. Cargar datos de prueba
0. Salir

Selecciona opción: 1
Nombre del doctor: Dr. Cardenas
Cédula: 12345
Especialidad: Cardiología
✓ Doctor registrado
```

---

## 💡 CARACTERÍSTICAS COMUNES

### En cada ejercicio interactivo encontrarás:

✅ **Agregar elementos** - Entrada manual de datos
✅ **Ver listado** - Mostrar todos los elementos
✅ **Buscar** - Filtrar por criterios específicos
✅ **Eliminar** - Remover elementos
✅ **Datos de prueba** - Cargar ejemplos automáticamente
✅ **Mensajes claros** - ✓ para éxito, ✗ para error

---

## 🔄 FLUJO TÍPICO DE USO

### Ejemplo con BibliotecaInteractiva:

1. **Ejecutar programa**
   ```bash
   java BibliotecaInteractiva
   ```

2. **Cargar datos de prueba** (Opción 6)
   ```
   Opción: 6
   ✓ Datos de prueba cargados
   ```

3. **Agregar un libro nuevo** (Opción 1)
   ```
   Opción: 1
   Título: Algoritmos
   Autor: Sedgewick
   ISBN: ISBN005
   Precio: 75
   ✓ Libro agregado
   ```

4. **Ver todos los libros** (Opción 2)
   ```
   Opción: 2
   1. Libro{...}
   2. Libro{...}
   ...
   ```

5. **Buscar por autor** (Opción 3)
   ```
   Opción: 3
   Autor: Robert Martin
   Libros de Robert Martin:
     - Clean Code (ISBN001)
     - Otro Libro (ISBN004)
   ```

6. **Salir** (Opción 0)

---

## 📂 ESTRUCTURA ACTUAL

```
FACIL/
├── Persona.java, Automovil.java, Rectangulo.java, Estudiante.java
├── Producto.java
└── TiendaInteractiva.java ⭐

MEDIO/
├── Libro.java, BibliotecaInteractiva.java ⭐
├── Empleado.java, EmpleadoFijo.java, EmpleadoTemporal.java
├── NominaInteractiva.java ⭐
├── Jugador.java, RankingInteractivo.java ⭐
├── Animal.java, Perro.java, Gato.java, Vaca.java, ZoologicoAnimales.java
└── Solido3D.java, Esfera.java, Cubo.java, Cilindro.java, SolidosGeometricos.java

DIFICIL/
├── CapacidadExcedidaException.java, ListaLimitada.java
├── EstadoReserva.java, Reserva.java, ReservasInteractiva.java ⭐
├── Volador.java, Nadador.java, AnimalInterfaz.java, Pato.java, Aguila.java, EjemploVoladores.java
├── VehiculoFactory.java, AutoFactory.java, MotoFactory.java, CamionFactory.java
└── VehiculosInteractiva.java ⭐, Sistema.java

EXTRA/
├── Persona.java, Paciente.java, Doctor.java
└── HospitalInteractivo.java ⭐
```

---

## ⚠️ NOTAS IMPORTANTES

- **Usa Scanner:** Espera entrada del usuario
- **Datos de prueba:** Presiona 5 para cargar ejemplos
- **Presiona 0:** Para salir del menú interactivo
- **Validación:** Algunos campos tienen validación (rango, fechas)
- **Sin comentarios:** El código es limpio y fácil de entender

---

## 🎓 APRENDIZAJE

Estos ejercicios enseñan:

- ✓ Manejo de colecciones (ArrayList)
- ✓ Entrada/salida (Scanner, System.out)
- ✓ Validación de datos
- ✓ Búsqueda y filtrado
- ✓ Eliminación de elementos
- ✓ Interfaz amigable con el usuario

---

**¡Listo para usar!** 🚀
