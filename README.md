# Splitwise
## Overview

  Splitwise is a SpringBoot Application designed to manage expenses and group finances. It allows users to create groups, add expenses and track how much each user owes or is owed within a group.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Schema](#schema)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Configuration](#configuration)
- [Contact](#contact)

## Introduction

Splitwise is a expense management application designed to simply group finances and ensure fair and transparent sharing of expenses. This project leverages Spring Boot to provide a robust backend infrastructure for handling users, groups, expenses and user interactions.

## Features

- User management 
- Group creation and management
- Expense tracking
- User expenses and settlements

## Schema

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 11 or higher
- Maven
- MySQL or your preferred database

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/SujiV-repo/Splitwise.git

2. Navigate to the project directory:

   ```bash
   cd Splitwise

3. Build the Project

   ```bash
   mvn clean install

## Usage

1. Running the application locally using the following command:

   ```bash
   mvn spring-boot:run

2. Visit 'http://localhost:8080' along with the API endpoint in you browser to access the Application.

## API Documentation

### Base URL
    ```bash
    http://localhost:8080/api-endpoint

### End Points
  #### Users

  **Creating Users**
  * **Endpoint:** 'POST/user/registeruser'
  * **Description:** Create/Register a new user 
  * **Request Body:**
    
          ```bash
          {
            "name" : "Mark",
            "email" : "mark45@gmail.com",
            "phoneNumber" : "8529647650"
          }
          
  * **Response:**
    
        ```bash
        {
            "name": "Mark",
            "email": "mark45@gmail.com",
            "phoneNumber": "8529647650"
        }
  
## Configuration
## Contact

