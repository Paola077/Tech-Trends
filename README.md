# Tech Trends API 📈
![CI](https://github.com/Paola077/Tech-Trends-Api/actions/workflows/ci.yml/badge.svg)

Tech Trends es una API REST diseñada para registrar, organizar y consultar tendencias tecnológicas emergentes. Proporciona funcionalidades completas de CRUD (Crear, Leer, Actualizar y Eliminar) para las entidades principales, incluyendo usuarios, tendencias y favoritos. Además, incluye un buscador avanzado para filtrar tendencias por categoría y título.

## Características Principales ✨

* Gestión de usuarios.
* Registro y administración de tendencias tecnológicas.
* Funcionalidad para agregar tendencias a favoritos por usuario.
* Buscador por categoría y título de tendencias.
* Almacenamiento de imágenes: Las imágenes asociadas a las tendencias se almacenan en Cloudinary.



## Pasos para Ejecutar la Aplicación y Pasar los Tests ⚙️

### Requisitos Previos:

* Java 21+
* Maven 
* Docker 
* render.com -> Donde se ha realizado el deployment
* Cuenta en Cloudinary

## Categorías Disponibles 📌

#### Las tendencias pueden ser clasificadas en las siguientes categorías fijas:

* AI: Inteligencia Artificial. 
* CLOUD: Computación en la nube.
* CYBERSECURITY: Ciberseguridad.
* DATA_SCIENCE: Ciencia de datos.
* DEVOPS: Prácticas DevOps.
* IOT: Internet de las cosas.
* BLOCKCHAIN: Tecnología blockchain.
* ROBOTICS: Robótica.
* AR_VR: Realidad Aumentada y Realidad Virtual.
* OTHER: Otras categorías no especificadas.

#### Utiliza estas categorías al crear o filtrar tendencias.

## Configuración🛠️


#### Clona el repositorio:

* git clone https://github.com/Paola077/Tech-Trends-Api
* cd tech-trends-Api

#### Configura los perfiles:

    Test: Base de datos H2 para pruebas.
    Dev: Base de datos PostgreSQL con Docker.
    Prod: Implementado en Render.

## Configura Cloudinary ☁️

* Crea una cuenta en Cloudinary.
* En tu archivo de configuración de entorno (.env), agrega:

      CLOUDINARY_URL=cloudinary://<api_key>:<api_secret>@<cloud_name>

* Sustituye <api_key>, <api_secret> y <cloud_name> por los valores específicos de tu cuenta.

## Construye la aplicación🏗️

    mvn clean install

## Ejecuta la aplicación🔧

### - Test:

    mvn spring-boot:run 
    spring.profiles.active=test

### - Dev:

    docker-compose up -d
    mvn spring-boot:run 
    spring.profiles.active=dev

### - Prod:

    Prod: La aplicación se encuentra desplegada en Render, no es necesario iniciar localmente.

## Almacenamiento de Imágenes:

* Todas las imágenes se almacenan en Cloudinary. 
* Asegúrate de configurar tu cuenta en Cloudinary y definir la variable de entorno CLOUDINARY_URL.

### Ejecuta los tests:

    mvn test

## Modelos E/R de la Base de Datos
### Diagrama Entidad-Relación (E/R)

![DiagramaERTechTrens.png](utils%2Fimages%2FDiagramaERTechTrens.png)

## Endpoints 🚀

### Usuarios

* Crear usuario

        POST /users
        {
        "username": "john_doe",
        "email": "john.doe@example.com",
        "password": "securePassword123"
        }

* Obtener todos los usuarios

      GET /users

* Obtener usuario por ID

       GET /users/{id}

* Actualizar usuario
    
        PUT /users/{id}
        {
        "email": "updated_name",
        "password": "password",
        }

* Eliminar usuario

        DELETE /users/{id}

### Tendencias

* Crear tendencia

        POST /trends
        {
        "title": "Latest Tech News",
        "description": "Updates about the newest gadgets and AI advancements.",
        "category": "Technology",
        "imgUrl": "https://example.com/tech-news.jpg",
        "userId": 1
        }

* Obtener todas las tendencias

      GET /trends

* Filtrar tendencias por categoría

      GET /trends?category=Technology

* Filtrar tendencias por título

      GET /trends?title=Technology

* Actualizar tendencia

        PUT /trends/{id}
        {
        "title": "Updated Tech News",
        "description":"Updated description",
        "imgUrl":"www.https://example.com/tech-news.jpg.com"
        }

* Eliminar tendencia

      DELETE /trends/{id}

## Favoritos

* Agregar a favoritos

        POST /favorites?userId=1&trendId=1

* Consultar favoritos

      GET /favorites?userId=1


## 🌐 Author

- **Paola Perdomo**                      
  [<img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />](https://github.com/Paola077)
  [<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" />](https://www.linkedin.com/in/paolaperdomo07/).
