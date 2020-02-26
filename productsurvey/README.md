# Project Description
This is a service which manages barang and survey data

## How it works
 - Create Barang
 - Get Barang
 - Create Survey
 - Find Survey by Barang
  
## Dev Environment Setup
* Java 8
```
$ ./mvnw clean install
$ ./mvnw clean build
```

##DDL
```
CREATE DATABASE product_survey;
USE product_survey;
CREATE TABLE IF NOT EXISTS barang (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nama` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=UTF-8;

CREATE TABLE `survey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kualitas` enum('baik','kurang_baik','rusak') NOT NULL DEFAULT 'baik',
  `barang_id` int(11) DEFAULT NULL,
  `surveyor_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF-8;
```