erDiagram
    USERS {
        integer id PK
        varchar name
        varchar login
        varchar email
        date birthday
    }
    MPA {
        integer id PK
        varchar name
        varchar description
    }
    GENRES {
        integer id PK
        varchar name
    }
    FILMS {
        integer id PK
        varchar name
        varchar description
        date release
        integer duration
        integer mpa FK
    }
    FILMS_GENRES {
        integer film_id FK
        integer genre_id FK
    }
    FRIENDS {
        integer user_one_id FK
        integer user_two_id FK
        boolean user_two_status
    }
    FILMS_LIKES {
        integer film_id FK
        integer user_id FK
        boolean like_status
    }

    USERS ||--o{ FRIENDS : "UserOne-UserTwo"
    USERS ||--o{ FILMS_LIKES : "Likes"
    USERS ||--o{ FILMS_LIKES : "Liked by"
    FILMS ||--o{ FILMS_GENRES : "Has Genres"
    GENRES ||--o{ FILMS_GENRES : "Genres of"
    MPA ||--o{ FILMS : "Rating"