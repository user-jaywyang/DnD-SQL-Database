# DnD Character & Gear Management – CS5200 Term Project

This project is a full-stack web application and relational database for managing **Dungeons & Dragons–style game characters**: their players, clans, jobs, stats, currencies, gear, and weapons.

It was built as a term project for a **Database Systems** course and demonstrates:

- A non-trivial **relational schema** with many interconnected entities
- A clean **DAO (Data Access Object)** layer in Java using JDBC
- A simple **JSP/Servlet** web UI for querying and updating data
- End-to-end flows from **database → Java model → servlet → JSP view**

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
  - [Project Structure](#project-structure)
  - [Key Packages](#key-packages)
- [Database Design](#database-design)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Database Setup](#database-setup)
  - [Running the Java Driver (Schema + Seed Data)](#running-the-java-driver-schema--seed-data)
  - [Deploying the Web App](#deploying-the-web-app)
- [Web UI Walkthrough](#web-ui-walkthrough)
- [Testing & Validation](#testing--validation)
- [Future Improvements](#future-improvements)
- [License](#license)

---

## Features

**Core functionality**

- **Search game characters by first name**  
  - Form-based search page (`FindGameChars.jsp`)
  - Results rendered in a table using JSTL
- **View weapons equipped by a character**
  - Displays weapons associated with a character, including:
    - Item level
    - Price
    - Max stack size
    - Required level
    - Job requirement
    - Attack damage
- **Update character information**
  - Example: update a character’s first name (`UpdateGameChar.jsp`)
  - Validates character ID and handles “not found” cases gracefully

**Backend & persistence**

- **Rich DnD-inspired domain model**, including:
  - Players, races, clans
  - Jobs and job requirements for gear
  - Currencies and character currency balances
  - Items, consumables, equippable items, gear, and weapons
  - Character stats, jobs, and inventory slots
- **DAO layer** that encapsulates:
  - Creating, reading, updating, and deleting records (CRUD)
  - Simple joins (e.g., fetching a character with their race, clan, weapon)
  - Query utilities (e.g., find characters by first name, list weapons by character)

**Schema management**

- Java driver (`DriverM3`) that can:
  - **Drop and recreate** the schema (`CS5200Project`)
  - **Create all database tables** programmatically (DDL inside Java text blocks)
  - **Insert seed records** for testing and demo purposes

---

## Tech Stack

**Languages & Frameworks**

- **Java 21** (Eclipse project using JavaSE-21)
- **Jakarta/Java EE Servlets & JSP**
- **JSTL** (JSP Standard Tag Library) for views

**Database**

- **MySQL 8.x**
- **JDBC** with `com.mysql.cj.jdbc.Driver`
- Schema name: `CS5200Project`

**Application Server / Tools**

- **Apache Tomcat 9.x** (configured via Eclipse)
- **Eclipse** Dynamic Web Project
- SQL script for schema: `CS5200_T3_Milestone2_Final.sql` (older milestone)
- Java-based schema creation: `DriverM3.resetSchema()`

---

## Architecture

This project follows a classic **MVC-style** setup with explicit layers:

- **Model layer** – Plain Java objects representing domain entities  
- **DAO layer** – All database access and SQL queries  
- **Web layer** – Servlets (controllers) + JSPs (views)  

### Project Structure

At a high level:

```text
CS5200_TermProject-main/
├── CS5200_T3_Milestone2_Final.sql   # Milestone 2 schema script (reference)
├── src/
│   └── main/
│       ├── java/
│       │   └── dnd/
│       │       ├── dal/             # DAOs + connection utilities
│       │       ├── model/           # Domain model classes
│       │       └── servlet/         # HTTP servlets
│       └── webapp/
│           ├── FindGameChars.jsp    # Search UI (home page)
│           ├── UpdateGameChar.jsp   # Update UI
│           ├── GameCharWeapons.jsp  # Weapons listing UI
│           ├── META-INF/
│           └── WEB-INF/
│               └── web.xml          # Web deployment descriptor
└── README.md                        # (this file)
