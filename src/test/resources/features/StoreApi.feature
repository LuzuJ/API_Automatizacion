# language: es
@StoreAPI
Característica: Gestión de Pedidos en la API de la Tienda de Mascotas

  Como automatizador de NTT,
  quiero verificar la creación y consulta de pedidos a través de la API de Store
  para garantizar la integridad de los datos y el correcto funcionamiento de los endpoints.

  @CrearPedido
  Esquema del escenario: Creación exitosa de un nuevo pedido con datos dinámicos
    Dado que preparo la solicitud para crear un nuevo pedido con id "<orderId>", petId "<petId>" y cantidad "<quantity>"
    Cuando envío la solicitud POST al endpoint "/store/order"
    Entonces el código de estado de la respuesta debe ser 200
    Y el cuerpo de la respuesta debe contener los detalles del pedido con id "<orderId>", petId "<petId>" y estado "placed"

    Ejemplos:
      | orderId | petId | quantity |
      | 9901    | 10    | 1        |
      | 9902    | 15    | 2        |

  @ConsultarPedido
  Esquema del escenario: Consulta exitosa de un pedido previamente creado
    # AÑADIMOS petId aquí para tener los datos para crear el pedido en la precondición
    Dado que un pedido con id "<orderId>" y petId "<petId>" fue creado
    Cuando envío la solicitud GET al endpoint "/store/order/<orderId>"
    Entonces el código de estado de la respuesta debe ser 200
    Y el cuerpo de la respuesta debe corresponder al pedido con id "<orderId>" y petId "<petId>"

    Ejemplos:
      | orderId | petId |
      | 9901    | 10    |
      | 9902    | 15    |
