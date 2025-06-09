FROM python:3.11-slim

WORKDIR /app
COPY shipments_producer.py .

RUN pip install kafka-python

CMD ["python", "shipments_producer.py"]
