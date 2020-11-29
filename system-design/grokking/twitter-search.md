# Design Twitter Search

## 1 Clarify Requirements

Functional requirements:

- Users can update tweets (plaintext)
- Design a system that can search all the user tweets

Non-Functional requirements:

- Let's assume Twitter has 1.5 billion total users with 800 million daily active users
- On average Twitter gets 400 million tweets every day
- The average tweet size is 300 bytes
- Let's assume there will be 500M searches every day
- Search query will consist of multiple words combined with AND/OR

Other assumptions:

- We want to design something

## 2 Back-of-the Envelop Estimations

Storage estimates:

- 300 bytes x 400M tweets per day = 120,000MB = 120GB per day
- 120GB per day / 86,400 seconds = 1.38MB per second

## 3 System Interface Definition

- GET `/api/v1/search?keywords=:encodedSearchTerms&devKey=:apiDevKey`

## 4 Database Design

Protocol Buffer:

```
syntax = "proto3";

import "google/protobuf/timestamp.proto";

message Tweet {
  string id = 1;
  int64 userId = 2;
  string status = 3;
  google.protobuf.Timestamp created = 4;
  google.protobuf.Timestamp lastModified = 5;
}
```

See below for how we would get data out of Elasticsearch.

## 5 High-Level Design

- Hadoop
  - MapReduce
  - HDFS
  - Protobuf / ORC
  - Spark / Flink
- Kafka
- Elasticsearch
- Load balancers
- Web servers
- Caching

## X Other Considerations

### Elasticsearch queries

See [Baeldung](https://www.baeldung.com/elasticsearch-full-text-search-rest-api) tutorial for original source.

Key concepts:

- `index` are stored in a set of shards, they are searched independently
- `type` is the ability to store several "types" of data in the same index

Thus in the example below we see the index is `text` and type is `article`

Let's discuss in detail the Elasticsearch queries:

```sh
# Suppose a document looks something like:
# {
#   "title": "He went",
#   "random_text": "He went such dare good fact. The small own seven saved man age."
# }

# We can check document count like:
curl -XGET 'http://localhost:9200/_count?pretty' -d '
	{
	  "query": {
	    "match_all": {}
	  }
	}'

# Or we can get a document using it's `id`
curl -XGET 'localhost:9200/text/article/1?pretty'
# {
#   "_index": "text",
#   "_type": "article",
#   "_id": "1",
#   "_version": 1,
#   "found": true,
#   "_source": {
#     "title": "He went",
#     "random_text":
#       "He went such dare good fact. The small own seven saved man age."
#   }
# }

# We can perform full-text search by doing the following:
curl -XGET 'localhost:9200/text/article/_search?pretty' -H 'Content-Type: application/json' -d '
	{
	  "query": {
	    "match": {
	      "random_text": "him departure"
	    }
	  }
	}'

# Fuzzy searches should be discouraged:
curl -XGET 'localhost:9200/text/article/_search?pretty' -H 'Content-Type: application/json' -d'
	{
	  "query":
	  {
	    "match":
	    {
	      "random_text":
	      {
	        "query": "him departure",
	        "fuzziness": "2"
	      }
	    }
	  }
	}'
```
