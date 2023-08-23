// VARIABLES

let limitByReceipt = document.getElementById("limit-by-receipt");
let tripAllowance = document.getElementById("trip-allowance");
let carUsage = document.getElementById("mileage-value");

// URLs

const url1 = 'http://localhost:8080/api/reimbursement/business-trip/exclude';
const url2 = 'http://localhost:8080/api/reimbursement/business-trip/include';
const url3 = 'http://localhost:8080/api/reimbursement/business-trip/allowance';
const url4 = 'http://localhost:8080/api/reimbursement/business-trip/duration';
const url5 = 'http://localhost:8080/api/reimbursement/admin/type';
const url6 = 'http://localhost:8080/api/reimbursement/business-trip/mileage';

// FUNCTIONS
async function getData(url) {
    try {
        const response = fetch(url);
        const data = (await response).json();
        console.log(data);
        return data;
    } catch (error) {
        console.error("Error: ", error)
    }
}

async function getTripAllowance() {
    getData(url3).then(data => tripAllowance.innerText = data);
}

function excludeDays() {
    getData(url1);
}

function includeDays() {
    getData(url2);
}

function getReceiptTypes() {
    const dropdown = document.getElementById("type-list");
    let defaultOption = document.createElement('option');
    defaultOption.text = '-choose type-';
    dropdown.appendChild(defaultOption);

    getData(url5).then(d => {
        d.forEach(i => {
            const option = document.createElement("option");
            option.innerText = i.receiptType;
            option.value = i.receiptType;
            dropdown.appendChild(option);
        });
    });
}

async function setTripDates() {
    try {
        const startDateInput = document.getElementById("date-start");
        const startDate = startDateInput.value;
        const durationInput = document.getElementById("trip-duration");
        const duration = durationInput.value;

        const data = {"startDate": startDate, "duration": duration};

        const response = await fetch(url4, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: JSON.stringify(data)
            }
        );
        await response.text();

    } catch (error) {
        console.error('Error:', error);
    }
}

async function getTripDates() {
    const checkboxContainer = document.getElementById("days-excluded");
    const data = await getData(url4);
    data.forEach(i => {
        const checkbox = document.createElement("checkbox");
        checkbox.innerText = i;
        checkbox.value = i;
        checkbox.appendChild(checkboxContainer);
    })
}

async function calculateMileage() {
    try {
        const mileageInput = document.getElementById("mileage-input");
        const mileage = mileageInput.value;

        const response = await fetch(url6, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: mileage
            }
        )
        carUsage.innerText = await response.text();
        console.log(response);
    } catch (error) {
        console.error('Error:', error);
    }
}

function initializeApp() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');

    document.getElementById("get-trip-allowance-btn").addEventListener("click", getTripAllowance);
    document.getElementById("exclude-days-btn").addEventListener("click", setTripDates);
    document.getElementById("show-exclude-days-btn").addEventListener("click", getTripDates);
    document.getElementById("calculate-by-mileage-btn").addEventListener("click", calculateMileage);
    checkboxes.forEach(ch =>
        ch.addEventListener("change", function () {
            if (ch.checked) {
                excludeDays();
            } else {
                includeDays();
            }
        }));

    getReceiptTypes();
}

window.addEventListener("DOMContentLoaded", initializeApp);