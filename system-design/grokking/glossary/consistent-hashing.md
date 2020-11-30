# Consistent Hashing

## Key Takeaways

- If you do simple hashing when you add a new node, you need to remap keys
  - Consistent hashing fixes this, only keys/nodes need to be remapped
- The basic idea is that imagine you have a ring:
  - Place nodes on the ring (ie. say ring range is [0, 256] and there are three nodes)
  - Map the key to an integer
  - Move clockwise from the integer to find the node that the key maps to
