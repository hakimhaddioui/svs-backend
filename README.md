# ⚙️ SVS Backend - API Rest (Spring Boot)

> **Partie Serveur** du Système de Veille Service (SVS) pour l'**ONCF**.  
> Développé en Java avec Spring Boot 3 et sécurisé par JWT.

---


## 🚀 Fonctionnalités Backend
- 🔐 **Sécurité & Auth** : Gestion de l'authentification avec **Spring Security** et **JWT**.
- 📋 **Gestion Métier** : Endpoints pour la création et le suivi des fiches **FEI** et **FEM**.
- ⚖️ **Workflow KN** : Logique de validation hiérarchique (Local -> Régional -> Central).
- 📊 **Moteur de Statistiques** : Agrégation des données pour les graphiques du Dashboard.
- 📂 **Reporting** : Services d'exportation de données pour les rapports PDF/Excel.

## 🏗️ Architecture Technique
Le backend suit une architecture en couches pour une meilleure maintenance :
- **Controller** : Exposition des points de terminaison (Endpoints) REST.
- **Service** : Logique métier et validation des règles ONCF.
- **Repository** : Interface avec la base de données via **Spring Data JPA**.
- **Model / Entity** : Mapping objet-relationnel (ORM) avec Hibernate.

## 🛠️ Stack Technique
- **Framework** : Spring Boot 3.x
- **Langage** : Java 17
- **Sécurité** : Spring Security, JSON Web Token (JWT)
- **Base de données** : PostgreSQL
- **Gestionnaire de dépendances** : Maven

## 🔧 Installation et Configuration

### 1. Prérequis
- Java JDK 17
- PostgreSQL 15+
- Maven 3.x

### 2. Configuration de la Base de Données
1. Créez une base de données PostgreSQL nommée `svs_db`
   ```
   sql CREATE DATABASE svs_db;
   ```
3. Ouvrez le fichier `src/main/resources/application.properties` et configurez vos accès :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/svs_db
spring.datasource.username=VOTRE_UTILISATEUR
spring.datasource.password=VOTRE_MOT_DE_PASSE

# Hibernate settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
Lancer le serveur Backend :
```bash 
mvn spring-boot:run
```
