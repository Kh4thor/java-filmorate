```mermaid
erDiagram
    USERS {
        int id PK
        varchar name
        varchar login
        varchar email
        date birthday
    }
    MPA {
        int id PK
        varchar name
        varchar description
    }
    GENRES {
        int id PK
        varchar name
    }
    FILMS {
        int id PK
        varchar name
        varchar description
        date release
        int duration
        int mpa FK
    }
    FILMS_GENRES {
        int film_id FK
        int genre_id FK
    }
    FRIENDS {
        int user_one_id FK
        int user_two_id FK
        boolean user_two_status
    }
    FILMS_LIKES {
        int film_id FK
        int user_id FK
        boolean like_status
    }

    USERS ||--o{ FRIENDS : ""
    USERS ||--o{ FILMS_LIKES : ""
    FILMS ||--o{ FILMS_GENRES : ""
    GENRES ||--o{ FILMS_GENRES : ""
    MPA ||--o{ FILMS : ""
    USERS ||--o{ FILMS_LIKES : ""
```
