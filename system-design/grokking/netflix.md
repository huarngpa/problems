# Design Netflix

## 1 Clarify Requirements

Functional:

- Users need to be able to upload videos
- Users need to be able to share and view videos
- Users need to be able to search video titles
- Record stats around videos
- Users need to be able to add and view comments

Non-Functional:

- System needs to be highly reliable, uploads should not be lost
- System needs to be highly available, consistency can be relaxed (ie. not being able to see an uploaded video for a while is OK)
- Users should have a real time experience while watching videos and should not feel any lag

## 2 Back-of-the Envelop Estimations

Assumptions:

- 1.5B total users
- 0.8B are daily active users
  - On average they view 5 videos per day, then 0.8B x 5 / 86400 sec = 46K videos/sec
- Upload to view ratio is 1:200
  - 46K / 200 = 230 videos/sec

### Storage estimates

Assumptions:

- Every minute 500 hours worth of videos uploaded
- One minute of video needs 50MB of storage

500 hours x 60 min x 50MB = 1,500,000 = 1.5TB/min = 25GB per second

Note this estimate changes when we include compression and replication.

### Bandwidth estimates

Assumptions:

- Each video takes a bandwidth of 10MB/min

500 hours x 60 min x 10MB/min = 300GB/min => 5GB/sec for uploads

5GB per sec x 200 = 1TB/sec for downloads

## 3 System Interface Definition

- GET `/api/stream/:id`
- POST `/api/upload/:id`
  - Form data:
    - Metadata
      - API developer key (useful for rate-limiting)
      - User ID
      - Title
      - Description
    - File
- PUT `/api/upload/:id`
  - See above

## 4 Database Design

```sql
create table video_metadata (
  id varchar(60),
  user_id bigint,
  title varchar(100),
  description varchar(255),
  original_file varchar(512), -- s3 bucket and key
  encodings text,
  /*
    {
      encodingType: {
        part: {
          location: [link]
        }
      }
    }
  */
  create timestamp with timezone,
  last_modified timestamp with timezone,
  public boolean
)
```

## 5 High-Level Design

The service components:

- Load balancing
- Write
  - Form upload
  - S3
  - Cassandra (metadata)
- Encoding
  - Kafka
  - S3
- Reads
  - HTTP Live Streaming (HLS)
  - Location services
  - ElasticSearch
  - CDN
