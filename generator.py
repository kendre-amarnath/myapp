import json
import random
from faker import Faker
from pymongo import MongoClient

fake = Faker('en_IN')
entries = []

for _ in range(50):
    file_number = random.randint(100000, 999999)
    entry = {
        "fileNumber": str(file_number),
        "firstName": fake.first_name(),
        "lastName": fake.last_name(),
        "gender": random.choice(["Male", "Female", "Other"]),
        "dateOfBirth": fake.date_of_birth(minimum_age=18, maximum_age=65).isoformat(),
        "address1": fake.address(),
        "address2": "",
        "phoneNumber1": fake.phone_number(),
        "phoneNumber2": ""
    }
    entries.append(entry)

with open("sample_data.json", "w") as f:
    json.dump(entries, f, indent=4)

print("50 sample entries saved to 'sample_data.json'")

client = MongoClient("mongodb://localhost:27017/")
db = client["UserData"]
collection = db["UserData"]

collection.delete_many({})


with open('sample_data.json') as file:
    file_data = json.load(file)

if isinstance(file_data, list):
    collection.insert_many(file_data)
else:
    collection.insert_one(file_data)

print(" The data has been uploaded to MongoDB (camelCase field names).")
