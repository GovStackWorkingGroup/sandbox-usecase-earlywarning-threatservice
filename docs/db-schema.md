# Database Schema

## Threat Table

| Name        | Type         | Description                                    |
|-------------|--------------|------------------------------------------------|
| id          | BIGINT       | Primary key, auto-incremented.                 |
| threatUUID  | UUID         | Unique identifier for the threat.              |
| type        | VARCHAR(255) | Type of the threat.                            |
| severity    | VARCHAR(255) | Severity level of the threat.                  |
| range       | VARCHAR(255) | Range of the threat.                           |
| notes       | VARCHAR(255) | Additional notes about the threat.             |
| periodStart | TIMESTAMP    | Start time of the threat period.               |
| periodEnd   | TIMESTAMP    | End time of the threat period.                 |
| createdAt   | TIMESTAMP    | Timestamp when the threat was created.         |

## Broadcast Table

| Name          | Type         | Description                                 |
|---------------|--------------|---------------------------------------------|
| id            | BIGINT       | Primary key, auto-incremented.              |
| broadcastUUID | UUID         | Unique identifier for the broadcast.        |
| threat_id     | BIGINT       | Foreign key referencing the threat table.   |
| title         | VARCHAR(255) | Title of the broadcast.                     |
| status        | VARCHAR(255) | Status of the broadcast. DRAFT, PUBLISHED   |
| notes         | VARCHAR(255) | Additional notes about the broadcast.       |
| englishMsg    | TEXT         | Message in English.                         |
| swahiliMsg    | TEXT         | Message in Swahili.                         |
| createdAt     | TIMESTAMP    | Timestamp when the broadcast was created.   |
| initiated     | TIMESTAMP    | Timestamp when the broadcast was initiated. |

## Country Threat Table

| Name          | Type         | Description                                    |
|---------------|--------------|------------------------------------------------|
| id            | BIGINT       | Primary key, auto-incremented.                 |
| threat_id     | BIGINT       | Foreign key referencing the threat table.      |
| countryId     | BIGINT       | Identifier for the country.                    |
| countryName   | VARCHAR(255) | Name of the country.                           |

## County Country Table

| Name            | Type         | Description                                    |
|-----------------|--------------|------------------------------------------------|
| id              | BIGINT       | Primary key, auto-incremented.                 |
| country_threat_id | BIGINT     | Foreign key referencing the country_threat table. |
| countyId        | BIGINT       | Identifier for the county.                     |
| countyName      | VARCHAR(255) | Name of the county.                            |