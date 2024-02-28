# AREP TALLER 4

Juan Pablo Poveda Cañón

En este taller se debe crear un servidor web en Java, similar al tipo Apache. Este servidor debe tener la capacidad de proporcionar páginas HTML y imágenes en formato PNG. Además, se espera que el servidor ofrezca un framework IoC (Inversión de Control) para construir aplicaciones web utilizando POJOS (Plain Old Java Objects). La aplicación web de ejemplo debe ser desarrollada utilizando este servidor, y se espera que el servidor pueda manejar múltiples solicitudes, aunque no de manera concurrente.

# Ejecución

Desde algun browser, empezamos a probar las siguientes direcciones:

http://localhost:35000

[![Search-Movies.png](https://i.postimg.cc/NfXH9Wrj/Search-Movies.png)](https://postimg.cc/dLq1gNBb)

http://localhost:35000/Saturno

[![Saturno-Movies.png](https://i.postimg.cc/T2FbPR1s/Saturno-Movies.png)](https://postimg.cc/kDNG1PxN)

## Arquitectura

* Gestor de Solicitudes:

Implementar un componente que gestione las solicitudes entrantes. Este componente debe ser capaz de recibir solicitudes HTTP y dirigirlas al controlador correspondiente.

* Controladores:

Definir controladores que manejen diferentes tipos de solicitudes. Estos controladores estarán encargados de procesar la solicitud, interactuar con el modelo y devolver una respuesta adecuada.

* Manejo de Estáticos:

Implementar un mecanismo para servir archivos estáticos como páginas HTML e imágenes en formato PNG. Esto podría hacerse mediante la creación de un manejador de archivos estáticos.
