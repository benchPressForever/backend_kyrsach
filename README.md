# Back Healthy Food App 🥗🍏

**Бэкенд-сервер для мобильного приложения здорового питания**  
REST API построено на современном стеке Java + Spring Boot.  
Серверная часть обрабатывает запросы от мобильного клиента (React Native), работает с базой данных PostgreSQL и упакована в Docker-контейнер для простого развертывания.

---

## 🧱 Стек технологий

- **Java 21** — язык разработки
- **Spring Boot 4** — основной фреймворк
- **Spring Web** — создание REST API
- **Spring Data JPA** — работа с базой данных
- **PostgreSQL** — реляционная база данных
- **Maven** — сборка и управление зависимостями
- **Docker / Docker Compose** — контейнеризация БД и приложения
- **Lombok** — генерация шаблонного кода

---

## 🐳 Запуск через Docker Compose

В корне проекта лежит файл **`docker-compose.yml`**, который поднимает базу данных PostgreSQL.  
Сам бэкенд пока запускается через IDE или Maven.

### 📋 Предварительные требования

- Установленный **Docker** и **Docker Compose**
- **JDK 17+**
- **Maven 3.8+**
- **Postman** (для тестирования API)

---

### 🚀 Пошаговый запуск

#### 1. Клонируйте репозиторий

```bash
git clone https://github.com/benchPressForever/backend_kyrsach
cd backend_kyrsach
```

#### 2. Запустите PostgreSQL через Docker Compose

```bash
docker-compose up -d
```

Эта команда:

- скачает образ PostgreSQL (если его нет)

- создаст контейнер с БД

- пробросит порт 5433 на локальную машину

#### Проверить, что контейнер запущен:

```bash
docker ps
```

Вы должны увидеть работающий контейнер с PostgreSQL.

#### 3. Соберите и запустите приложение

Для этого просто нажми на кнопку запуска или нажмите на Shift + F10

<img width="466" height="48" alt="image" src="https://github.com/user-attachments/assets/d6819e3e-8765-4d73-948d-937ae7d0ca16" />

#### 4. Проверьте работу API

Откройте браузер или Postman и перейдите по адресу:

GET

```bash
http://localhost:9090/ping
```

Ожидаемый ответ:

```bash
{
  "message": "Pong"
}
```


