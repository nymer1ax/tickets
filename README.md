# Proyecto Base Implementando Clean Architecture


## Endpoints 

### Create Ticket
`POST api/tickets`

```
Body:
{
    "userId": 456,
    "status": true
}
```
```
Response:
{
    "id": 1,
    "userId": 456,
    "creationDate": "2023-07-23T13:29:42.245242",
    "updateDate": null,
    "status": true
}
```

### Update Ticket
`PUT api/tickets`

```
Body:
{
    "id": 1,
    "userId": 456,
    "status": false
}
```
```
Response:
{
    "id": 1,
    "userId": 456,
    "creationDate": "2023-07-23T13:29:42.245242",
    "updateDate": "2023-07-23T13:31:42.195407",
    "status": false
}
```

### Delete Ticket
`PUT api/tickets`

```
Body:
{
    "ticketId": 1
}
```
```
Response:
void 202 Accepted
```
### Get All Ticket
`GET api/tickets`

RequestParam:
- ?page
- ?size

```
Response:
{
    "content": [
        {
            "id": 2,
            "userId": 457,
            "creationDate": "2023-07-23T13:33:20.127671",
            "updateDate": null,
            "status": true
        },
        {
            "id": 3,
            "userId": 458,
            "creationDate": "2023-07-23T13:33:24.340639",
            "updateDate": null,
            "status": true
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
}
```

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**
