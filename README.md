# Spring Boot & Kafka

#### Программа для демонстрации работы Spring Boot и Kafka

Использованные технологии:

- Spring Boot
- Kafka

#### Docker

Поднять Kafka в докере можно с помощью этого файла docker-compose.yml:

    version: "3"
    services:
      zookeeper:
        image: wurstmeister/zookeeper
        container_name: zookeeper
        ports:
          - "2181:2181"
      kafka:
        image: wurstmeister/kafka
        container_name: kafka
        ports:
          - "9092:9092"
        depends_on:
          - zookeeper
        environment:
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
          KAFKA_ADVERTISED_HOST_NAME: localhost

#### Отправка сообщений

В Kafka реализованы 3 топика для отправки и получения сообщений.

Превый топик принимает сообщения методом POST по эндпоинту /api/v1/send/msg

Формат сообщения JSON:

    {
      "author":"Кирилл",
      "text":"Проверка текста"
    }

Второй топик принимает сообщения методом POST по эндпоинту /api/v1/send/payment

Формат сообщения JSON:

    {
      "amount": 20000,
      "status": "DECLINED",
      "timestamp": [
      2023,
      10,
      19,
      15,
      7,
      52,
      31529400
      ]
    }

Поле status может принимать только значения:  RESERVED, ACCEPTED, DECLINED

Третий топик принимает сообщения методом POST по эндпоинту /api/v1/send/log

Формат сообщения JSON:

    {
      "systemName": "SERVER_1",
      "text": "Крэш",
      "timestamp": [
      1986,
      4,
      8,
      12,
      30
      ]
    }

Поле systemName может принимать только значения: SERVER_1, SERVER_2, SERVER_3, SERVER_4, SERVER_5,SERVER_6

#### Получение сообщений

Получение сообщений методом POST по эндпоинту /api/v1/get

Формат сообщения JSON:

    {
     "group":"mygroup",
     "topic":"MESSAGE"
    }

Поле group произвольное название группы консьюмеров, для того, что можно было реализовать получение только новых
сообщений (сейчас получение всех).
Поле topic может принимать только значения: MESSAGE, PAYMENT, LOG_EVENT

