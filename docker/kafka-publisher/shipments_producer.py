import json
import time
import random
from datetime import datetime, timedelta
from kafka import KafkaProducer
from kafka.errors import NoBrokersAvailable

ORIGINS = ['Hamburg', 'Berlin', 'Munich', 'Hanover']
DESTINATIONS = ['Cologne', 'Frankfurt', 'Leipzig', 'Brunswick']

def generate_shipment():
    return {
        "shipmentId": f"SHIP-{random.randint(1000, 9999)}",
        "origin": random.choice(ORIGINS),
        "destination": random.choice(DESTINATIONS),
        "status": "CREATED",
        "createdAt": datetime.now().strftime("%Y-%m-%dT%H:%M:%S")
    }

# Wait for Kafka to be ready
while True:
    try:
        producer = KafkaProducer(
            bootstrap_servers='kafka:9092',
            value_serializer=lambda v: json.dumps(v).encode('utf-8')
        )
        print("✅ Connected to Kafka.")
        break
    except NoBrokersAvailable:
        print("❌ Kafka not ready, retrying...")
        time.sleep(5)

# Send messages every 5 seconds
while True:
    shipment = generate_shipment()
    print(f"📦 Sending: {shipment}")
    producer.send('shipment.created', shipment)
    time.sleep(5)
