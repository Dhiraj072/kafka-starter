# Kafka-Starter

A tutorial project for [Apache Kafka](https://kafka.apache.org/)

# Notes
## Kafka components
### Zookeeper
Keeps track of all components

`$ zookeeper-server-start.sh config/zookeeper.properties`
### Kafka server
The broker service that receives and delivers messages

`$ kafka-server-start.sh config/server.properties`

### Producer
Service that produces messages

### Consumer
Service that recceives messages

`$ kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic first_topic --group group-first`

## Points to note
* Each topic may have one or more partitions
* Partitions are distributed across various kafka brokers
* Each consumer is assigned to a certain number of partitions
* Consumer re-balancing occurs within a consumer group when a consumer joins/leaves the group
* Consumer `Assign` and `Seek` APIs for recovery 

## Built With

* [Apache Kafka](https://kafka.apache.org/) - Open-source distributed event streaming platform
* [Gradle](https://gradle.org/) - Build tool
* [JUnit](https://junit.org/) - Testing Framework


## Authors

* [Dhiraj](https://github.com/dhiraj072)

