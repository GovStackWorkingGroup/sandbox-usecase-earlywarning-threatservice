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

| Name                | Type         | Description                                 |
|---------------------|--------------|---------------------------------------------|
| id                  | BIGINT       | Primary key, auto-incremented.              |
| broadcastUUID       | UUID         | Unique identifier for the broadcast.        |
| threat_id           | BIGINT       | Foreign key referencing the threat table.   |
| title               | VARCHAR(255) | Title of the broadcast.                     |
| status              | VARCHAR(255) | Status of the broadcast.                    |
| notes               | VARCHAR(255) | Additional notes about the broadcast.       |
| countryId           | BIGINT       | Identifier for the country.                 |
| countryName         | VARCHAR(255) | Name of the country.                        |
| primaryLangMessage  | TEXT         | Message in the primary language.            |
| secondaryLangMessage| TEXT         | Message in the secondary language.          |
| periodStart         | TIMESTAMP    | Start time of the broadcast period.         |
| periodEnd           | TIMESTAMP    | End time of the broadcast period.           |
| createdAt           | TIMESTAMP    | Timestamp when the broadcast was created.   |
| initiated           | TIMESTAMP    | Timestamp when the broadcast was initiated. |

## Country Threat Table

| Name          | Type         | Description                                    |
|---------------|--------------|------------------------------------------------|
| id            | BIGINT       | Primary key, auto-incremented.                 |
| threat_id     | BIGINT       | Foreign key referencing the threat table.      |
| countryId     | BIGINT       | Identifier for the country.                    |
| countryName   | VARCHAR(255) | Name of the country.                           |

## County Country Table

| Name              | Type         | Description                                    |
|-------------------|--------------|------------------------------------------------|
| id                | BIGINT       | Primary key, auto-incremented.                 |
| country_threat_id | BIGINT       | Foreign key referencing the country_threat table. |
| countyId          | BIGINT       | Identifier for the county.                     |
| countyName        | VARCHAR(255) | Name of the county.                            |

## Broadcast County Table

| Name          | Type         | Description                                    |
|---------------|--------------|------------------------------------------------|
| id            | BIGINT       | Primary key, auto-incremented.                 |
| broadcast_id  | BIGINT       | Foreign key referencing the broadcast table.   |
| countyId      | BIGINT       | Identifier for the county.                     |
| countyName    | VARCHAR(255) | Name of the county.                            |

## Database Change Log Table

| Name          | Type         | Description                                    |
|---------------|--------------|------------------------------------------------|
| id            | VARCHAR(255) | Unique identifier for the change log.          |
| author        | VARCHAR(255) | Author of the change log.                      |
| filename      | VARCHAR(255) | Filename of the change log.                    |
| dateExecuted  | TIMESTAMP    | Timestamp when the change log was executed.    |
| orderExecuted | INTEGER      | Order in which the change log was executed.    |
| execType      | VARCHAR(10)  | Type of execution.                             |
| md5sum        | VARCHAR(35)  | MD5 checksum of the change log.                |
| description   | VARCHAR(255) | Description of the change log.                 |
| comments      | VARCHAR(255) | Comments about the change log.                 |
| tag           | VARCHAR(255) | Tag associated with the change log.            |
| liquibase     | VARCHAR(20)  | Liquibase version used.                        |
| contexts      | VARCHAR(255) | Contexts in which the change log was executed. |
| labels        | VARCHAR(255) | Labels associated with the change log.         |
| deployment_id | VARCHAR(10)  | Deployment identifier.                         |

## Database Change Log Lock Table

| Name        | Type         | Description                                    |
|-------------|--------------|------------------------------------------------|
| id          | INTEGER      | Unique identifier for the lock.                |
| locked      | BOOLEAN      | Indicates if the lock is active.               |
| lockGranted | TIMESTAMP    | Timestamp when the lock was granted.           |
| lockedBy    | VARCHAR(255) | Identifier of the entity that holds the lock.  |