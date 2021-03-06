# Design TinyURL

## 1 Clarify Requirements

Goal:

- Take a long URL and make a shorter one

Functional requirements:

- Shortens a URL
- Service redirects to original link
- Can pick a custom short link for their URL
- Links expire and user can specify when

Non-Functional requirements:

- System needs to be highly available
- Redirection should happen in real-time with minimal latency
- Shortened links should not be guessable

Other requirements:

- Analytics
- Needs a REST API

## 2 Back-of-the Envelop Estimations

### Scale

- System will be **read** heavy 100:1 ratio between read and writes
- **500M** URL shortening requests per month

500M x 100 reads = 50,000M or 50B reads

50,000M / 2.6M = 20K RPS (reads)

500M / 2.6M = 200 RPS (writes)

### Storage estimates

500M x 3 months avg exp x 12 months = 18,000M or 18B records

Schema:

- id
- redirect_url
- expiration
- creation
- userId

Let's assume 500 bytes for ease of calculations.

18B x 500 = 9,000B bytes = 9TB of storage.

### Bandwidth estimates

20K RPS x 500 Bytes = 10,000K Bytes per second = 10 MB/s read bandwidth

200 RPS x 500 Bytes = 100,000 Bytes per second = 100 KB/s write bandwidth

### Memory estimates

It's good to think about this for caching.

We can follow 80-20 rule here assuming that 20% of URLs generate 80% of the traffic, we can assume:

9TB x 20% / 12 = 150GB of cache memory.

## 3 System Interface Definition

- POST /api/shortener
  - apiKey
  - originalUrl
  - expiresAt
  - email
  - customAlias (optional)
- DELETE /api/shortener
  - apiKey
  - id
  - email
- GET /:id

Make sure to mention that we can prevent abuse by limiting number of created URLs

## 4 Database Design

NoSQL is the better choice here

## 5 High-Level Design

- Load balancing
- Consider breaking up the key generation as a separate service
- Clean-up service to remove expired links
- Caching
  - Between app and database
- NoSQL
- Telemetry
  - Possibly lambda architecture
  - Datadog

## X Follow-Ups

- Data partitioning
  - Range-based partitioning (ie. As, Bs, etc.)
    - Not great, unbalanced load
  - Hash-based partitioning (see consistent hashing)
- Permissioning? Think of ACLs or cgroups in Linux, can you implement something similar
