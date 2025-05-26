# 📌 **Práctica 2: Sistema de Gestión de Compras**

### 🤝 **Integrantes:** Andrés Cajas, Brandon Collaguazo.

------------

### 📘 **Descripción**
Este programa es un sistema de gestión de compras desarrollado en Java. Permite registrar y administrar información relacionada con proveedores, empleados, productos y solicitudes de compra de forma sencilla y organizada mediante una interfaz basada en menús.

------------

### 📖 **Funcionalidades**
- Registro de proveedores, empleados y productos.
- Creación y gestión de solicitudes de compra.
- Cálculo automático de subtotales, IVA y totales de solicitudes.
- Búsqueda de proveedores, empleados y productos por criterios específicos.
- Interfaz de usuario basada en menú para una fácil navegación.

------------

### 🖼️ **Diagrama**

  ![image](https://github.com/user-attachments/assets/309b5f46-13ff-4fc5-81a1-6b624abbb79e)

------------

### 💻 **Estructura**
- `ec.edu.est.poo.modelos`: Clases que representan los objetos del sistema: Empleado, Proveedor, Producto y SolicitudCompra.
- `ec.edu.est.poo.vista`: Clases que gestionan la interfaz de usuario, principalmente ventanas y menús.

-----------

### ¿Cómo funciona?
El sistema utiliza clases para modelar los diferentes elementos (empleados, proveedores, productos y solicitudes). La lógica está separada en controladores que gestionan los datos y actualizan la interfaz cuando el usuario realiza alguna acción, como agregar un producto o crear una solicitud nueva. Los cálculos de montos se hacen automáticamente al añadir productos a las solicitudes, incluyendo el cálculo del IVA.
