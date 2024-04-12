#!/bin/bash

# Base URL for the PUT request
URL="http://localhost:9088/tracking"

# Content type header
CONTENT_TYPE="application/json"

# Asset ID (replace with your actual ID)
ASSET_ID=1

# Initial latitude and longitude (adjust starting point)
LATITUDE=35.21657509859073
LONGITUDE=10.217740091700108

# Number of PUT requests to send (adjust for desired simulation length)
NUM_REQUESTS=100

# Movement increment (adjust for desired movement distance)
DELTA=1

# Loop for sending PUT requests
for (( i=0; i<$NUM_REQUESTS; i++ )); do
  # Update latitude and longitude with slight variation
 NEW_LATITUDE=$(echo "$LATITUDE + $DELTA" | awk '{printf "%.10f", ($1 + $2)}')
NEW_LONGITUDE=$(echo "$LONGITUDE + $DELTA" | awk '{printf "%.10f", ($1 + $2)}')

  # Construct JSON payload using string interpolation
  PAYLOAD="{
    \"assetId\": $ASSET_ID,
    \"status\": \"ACTIVE\",
    \"latitude\": $NEW_LATITUDE,
    \"longitude\": $NEW_LONGITUDE
  }"

  # Send PUT request using curl
  curl -X PUT \
    -H "Accept: application/json" \
    -H "Content-Type: $CONTENT_TYPE" \
    -d "$PAYLOAD" \
    "$URL"

  # Optional: Add a delay between requests (adjust for desired movement speed)
  sleep 5

  # Update initial values for next iteration
  LATITUDE=$NEW_LATITUDE
  LONGITUDE=$NEW_LONGITUDE

  echo "Sent  PUT requests simulating asset movement."


done

echo "Sent $NUM_REQUESTS PUT requests simulating asset movement."
