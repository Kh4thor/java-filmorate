erDiagram
	user ||--|| friends : references
	user ||--|| likes : references
	likes ||--|| film : references

	likes {
		INTEGER user_id
		INTEGER film_id
	}

	user {
		INTEGER user_id
		VARCHAR(255) name
		VARCHAR(255) login
		VARCHAR(255) email
		DATE birthday
	}

	film {
		INTEGER film_id
		VARCHAR(255) name
		TEXT description
		DATE releaseDate
		INTEGER duration
	}

	friends {
		INTEGER user_id
		INTEGER friend_id
		SMALLINT status
	}