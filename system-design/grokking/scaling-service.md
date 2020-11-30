# Scaling Services

## 1 Clarify Requirements

These questions really boil down to:

1. Profiling the application
2. Identifying the bottleneck
3. Determining tradeoffs and applying solutions
4. Repeat

## 2 Back-of-the Envelop Estimations

## 3 System Interface Definition

## 4 Database Design

## 5 High-Level Design

Let's just go through a couple of scenarios:

- Vertical scaling:
  - Database needs more IOPS
  - Servers need more CPU and/or memory
- Horizontal scaling:
  - Deploy more pods/servers
- Database-specific:
  - Indexing
  - Caching
  - NoSQL instead (ie. Cassandra)
- Application-specific:
  - Some inefficiency at the app-level holding us back
