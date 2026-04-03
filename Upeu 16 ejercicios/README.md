# EJERCICIOS DE PROGRAMACIÓN ORIENTADA A OBJETOS EN JAVA

**30 Ejercicios Prácticos** con dificultad progresiva

---

## ESTRUCTURA DEL PROYECTO

```
Uperu 16 ejerciciosde de clase/
├── MenuPrincipal.java
├── FACIL/
│   ├── Persona.java
│   ├── Automovil.java
│   ├── Rectangulo.java
│   ├── Estudiante.java
│   ├── Producto.java
│   └── Tienda.java
├── MEDIO/
│   ├── Libro.java
│   ├── Biblioteca.java
│   ├── Empleado.java
│   ├── EmpleadoFijo.java
│   ├── EmpleadoTemporal.java
│   ├── NominaEmpleados.java
│   ├── Jugador.java
│   ├── Ranking.java
│   ├── Animal.java
│   ├── Perro.java
│   ├── Gato.java
│   ├── Vaca.java
│   ├── ZoologicoAnimales.java
│   ├── Solido3D.java
│   ├── Esfera.java
│   ├── Cubo.java
│   ├── Cilindro.java
│   └── SolidosGeometricos.java
├── DIFICIL/
│   ├── CapacidadExcedidaException.java
│   ├── ListaLimitada.java
│   ├── EstadoReserva.java
│   ├── Reserva.java
│   ├── CentralReservas.java
│   ├── Volador.java
│   ├── Nadador.java
│   ├── AnimalInterfaz.java
│   ├── Pato.java
│   ├── Aguila.java
│   ├── EjemploVoladores.java
│   ├── VehiculoFactory.java
│   ├── AutoFactory.java
│   ├── MotoFactory.java
│   ├── CamionFactory.java
│   ├── FabricaVehiculos.java
│   └── Sistema.java
└── EXTRA/
    ├── Persona.java
    ├── Paciente.java
    ├── Doctor.java
    └── Hospital.java
```

---

## CÓMO EJECUTAR

### Opción 1: Usar el Menú Principal

```bash
cd "c:\Users\USUARIO\Documents\upeuclasessistemassss\Uperu 16 ejerciciosde de clase"
javac MenuPrincipal.java FACIL/*.java MEDIO/*.java DIFICIL/*.java EXTRA/*.java
java MenuPrincipal
```

### Opción 2: Ejecutar Directamente

Cada archivo tiene un método `main()` y puede ejecutarse independientemente.

```bash
cd FACIL
javac Persona.java
java Persona
```

---

## NIVELES Y EJERCICIOS

### NIVEL FÁCIL (5 ejercicios)

1. **Persona** - Clase base con atributos privados, getters/setters con validación
2. **Automóvil** - Métodos de simulación (acelerar, frenar)
3. **Rectángulo** - Cálculos geométricos (área, perímetro)
4. **Estudiante** - Validación de rango de datos
5. **Tienda** - ArrayList básico con búsqueda y eliminación

**Conceptos:** Encapsulamiento, Getters/Setters, ArrayList, Validación

---

### NIVEL MEDIO (5 ejercicios)

1. **Biblioteca de Libros** - ArrayList avanzado con búsqueda filtrada
2. **Empleado Fijo/Temporal** - Clases abstractas y herencia polimórfica
3. **Ranking** - Ordenamiento con Comparator
4. **Animales** - Polimorfismo y sobrescritura de métodos
5. **Sólidos 3D** - Clases abstractas con múltiples implementaciones

**Conceptos:** Herencia, Polimorfismo, Clases Abstractas, Interfaces de Ordenamiento

---

### NIVEL DIFÍCIL (5 ejercicios)

1. **Lista Limitada** - Excepciones personalizadas
2. **Sistema de Reservas** - Enums y filtrado avanzado
3. **Interfaces Múltiples** - Implementación de múltiples interfaces
4. **Factory Pattern** - Patrón de diseño para creación de objetos
5. **Clase Interna** - Inner classes para configuración

**Conceptos:** Excepciones, Enums, Interfaces Múltiples, Factory Pattern, Inner Classes

---

### NIVEL EXTRA - MÁXIMA DIFICULTAD

1. **Mini Sistema Hospitalario** - Jerarquía completa de 4 niveles
   - Persona (clase base abstracta)
   - Paciente y Doctor (herencia)
   - Hospital (gestor principal)
   - ArrayList complejo con relaciones

**Conceptos:** Herencia Multinivel, Composición, ArrayList de Objetos, Reportes

---

## CONCEPTOS CLAVE APLICADOS

### Fácil
- ✓ Clases y Objetos
- ✓ Encapsulamiento (private/public)
- ✓ Getters y Setters
- ✓ Validación de datos
- ✓ ArrayList básico

### Medio
- ✓ Herencia (extends)
- ✓ Clases Abstractas
- ✓ Polimorfismo
- ✓ Métodos abstractos
- ✓ Comparator para ordenamiento

### Difícil
- ✓ Excepciones personalizadas
- ✓ Enums
- ✓ Interfaces (Implementación múltiple)
- ✓ Factory Pattern
- ✓ Inner Classes
- ✓ Genéricos (ArrayList<T>)

### Extra
- ✓ Herencia Multinivel
- ✓ Composición de objetos
- ✓ Relaciones complejas
- ✓ Métodos de utilidad (reportes)
- ✓ ArrayList anidados

---

## NOTAS IMPORTANTES

- **Sin comentarios:** Todo el código está limpio sin comentarios explicativos
- **Métodos main():** Todos los archivos tienen un main() para pruebas directas
- **Encapsulamiento:** Se respetan los principios de acceso (private/public)
- **Validación:** Se incluyen validaciones en setters
- **Excepciones:** Se manejan adecuadamente en casos difíciles

---

## CRÉDITOS

Ejercicios desarrollados siguiendo programación orientada a objetos en Java.
Niveles: Fácil → Medio → Difícil → Extra
