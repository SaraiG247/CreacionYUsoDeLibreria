# Creación de una librería que genere CURP y RFC

Esta librería permite validar la estructura y autenticidad de claves CURP (Clave Única de Registro de Población) y RFC (Registro Federal de Contribuyentes), dichas clases están diseñadas para generar y validar claves de identificación mexicanas. A partir de estos datos personales como nombre,a apellidos fecha de nacimiento, sexo y entidad federativa, se integran en sistemas que requieras validar estas claves, como sistemas escolares, bancarios o de gobierno.

## GeneradorCurp

Su propósito es generar una curp válida siguiendo las normas del RENAPO (Registro Nacional de Población) 

Se toman los valores o parámetros necesarios para identificar un generador de Curp:
[![Captura-de-pantalla-2025-04-13-163615.png](https://i.postimg.cc/x1DNBdz5/Captura-de-pantalla-2025-04-13-163615.png)](https://postimg.cc/8j4C6Nwf)

Se identifica el sexo y estado. En esta parte el sexo se codifica como “H” (hombre) o “M” (Mujer), dicho estado se representa mediante una clave oficial de dos letras.
[![image.png](https://i.postimg.cc/DZ33rtJx/image.png)](https://postimg.cc/t1kcyvz6)

Se realiza la extracción de la primera letra y la primera vocal interna del apellido paterno, así como la primera del apellido materno y la primera letra del nombre.

[![image.png](https://i.postimg.cc/MHWTDcM3/image.png)](https://postimg.cc/HchdWktb)

Se utiliza un algoritmo para asegurar unicidad en el último bloque de la CURP
[![image.png](https://i.postimg.cc/FKykVXCR/image.png)](https://postimg.cc/FfKKh845)

Dicha Clase devuelve una CURP como String de 18 Caracteres.

## GeneradorRFC
El propósito es calcular el RFC de una persona física a partir de sus datos personales, conforme a las reglas del SAT.
[![image.png](https://i.postimg.cc/WpGjpbFt/image.png)](https://postimg.cc/c6LPXWcN)

Se extraen las letras, la 1 letra y primera vocal del apellido paterno, 1 letra del apellido materno y la 1 letra del nombre 
Se realizan combinaciones de 3 caracteres aleatorios, numéricos y alfabéticos. Y así mismo su única salida sería el RFC compuesto por 13 caracteres.

## GeneradorIdentificación
Esta clase integradora ofrece métodos para generar CURP y RFC al mismo tiempo, facilitando su uso en interfaces.
[![image.png](https://i.postimg.cc/h40Dbb8K/image.png)](https://postimg.cc/NL5vQXPn)

## Importar la librería .jar
La librería Validacion_CurpRfc está compilada como un archivo .jar (Java Archive), lo que permite reutilizar sus clases en múltiples proyectos Java sin necesidad de copiar el código fuente. Esta es una práctica común en el desarrollo de software para fomentar la modularidad, el mantenimiento y la distribución de funcionalidades encapsuladas.

¿Qué es un archivo .JAR?
Un archivo .jar es un archivo comprimido que contiene:
- Archivos `.class` ya compilados (código Java listo para ejecutar).
- Un archivo de manifiesto (`MANIFEST.MF`) con metadatos del proyecto.
- Opcionalmente, recursos como:
  - Imágenes
  - Archivos de propiedades
  - Bibliotecas adicionales

En nuestro caso, el archivo Validacion_CurpRfc.jar contiene las clases:
- GeneradorCURP.class
- GeneradorRFC.class
- GeneradorIdentificacion.class

### Obtener el archivo JAR

- **Ruta:** `Validación Curp Rfc/dist/Validacion_CurpRfc.jar`

### Importar en NetBeans

1. Haz clic derecho en el proyecto.
2. Ve a **Propiedades**.
3. Selecciona **Bibliotecas**.
4. Haz clic en **Agregar JAR/carpeta**.
5. Busca y selecciona el archivo `Validacion_CurpRfc.jar`.

[![image.png](https://i.postimg.cc/L6ZfN8D9/image.png)](https://postimg.cc/svsv2ybq)

y se busca el archivo JAR donde se guardó, y se selecciona.
[![image.png](https://i.postimg.cc/hvJBVw9j/image.png)](https://postimg.cc/Mf8NS932)

Por último se importuna las clases en el código que se ocupen.

[![image.png](https://i.postimg.cc/bvrJJ6s6/image.png)](https://postimg.cc/cKqZkB7Y)

## Prueba de JFRAME
La clase Generador CURP RFC extiende javax.swing.JFrame y representa una interfaz visual para que el usuario pueda introducir sus datos personales y obtener automáticamente su CURP y su RFC utilizando la librería Validacion_CurpRfc.jar.
Esta clase permite la interacción entre el usuario final y el backend de generación de claves. Está diseñada para uso práctico y demostrativo, siendo una excelente herramienta de validación visual en contextos escolares o administrativos.
### Estructura visual de los componentes
[![Captura-de-pantalla-2025-04-09-231345.png](https://i.postimg.cc/qq90G3Hy/Captura-de-pantalla-2025-04-09-231345.png)](https://postimg.cc/7JMpHb6Z)

### Ejemplos
[![Captura-de-pantalla-2025-04-09-215917.png](https://i.postimg.cc/HWF5qBwm/Captura-de-pantalla-2025-04-09-215917.png)](https://postimg.cc/w3c7J544)
[![Captura-de-pantalla-2025-04-09-220136.png](https://i.postimg.cc/ydH9JdNN/Captura-de-pantalla-2025-04-09-220136.png)](https://postimg.cc/rdfz3qx6)

### Evento del Botón

Este evento se ejecuta cuando el usuario hace clic en el botón **"Generar"**. Realiza las siguientes acciones:

1. Recopila los datos ingresados en el formulario.
2. Determina el sexo con base en el **radio button** seleccionado.
3. Llama a los métodos estáticos de la librería `GeneradorCURP` y `GeneradorRFC`.
4. Muestra los resultados en los campos `txtCurp` y `txtRfc`.
[![image.png](https://i.postimg.cc/SRzHsD18/image.png)](https://postimg.cc/hQc2ZbBP)
- Intuitiva: cualquier persona puede llenar el formulario y generar sus claves.
- Eficiente: permite generar en segundos lo que manualmente podría llevar minutos.
- Extensible: puede integrarse fácilmente con bases de datos o sistemas escolares.

  ## Video sobre el uso de la librería
  [Ver video sobre el uso de la libreria en YouTube](https://youtu.be/QzNLyj4i-0s)

