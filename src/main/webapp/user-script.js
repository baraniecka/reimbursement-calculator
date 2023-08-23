// VARIABLES

let dateStart = document.getElementById("date-start");
let tripDuration = document.getElementById("trip-duration");
let typeList = document.getElementById("receipt-types");
let newExpenseType = document.getElementById("new-expense-type");
let limitByReceipt = document.getElementById("limit-by-receipt");
let tripAllowance = document.getElementById("trip-allowance");

// URLs

const url1 = '/api/reimbursement/business-trip/exclude';
const url2 = '/api/reimbursement/business-trip/include';
const url3 = '/api/reimbursement/business-trip/allowance';
const url4 = '/api/reimbursement/business-trip/duration';
const url5 = 'http://localhost:8080/api/reimbursement/admin/type';
const url6 = '';

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

// TODO - rozbić na dwie funkcje
async function setTripDates() {
    try {
        const startDateInput = document.getElementById("date-start");
        const startDate = startDateInput.value;
        const durationInput = document.getElementById("trip-duration");
        const duration = durationInput.value;

        const data = {
            "startDate": startDate,
            "duration": duration
        };

        const response = await fetch(url4, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: JSON.stringify(data)
            }
        );
        return response.text();

    } catch (error) {
        console.error('Error:', error);
    }
}

async function getTripDates() {
try {
    const dates = await setTripDates();

    const checkboxContainer = document.getElementById("days-excluded");
    dates.forEach(i => {
        const checkbox = document.createElement("checkbox");
        checkbox.innerText = i;
        checkbox.value = i;
        checkbox.appendChild(checkboxContainer);
    });
}catch(error){
    console.error(error);
}
}


function initializeApp() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');

    document.getElementById("get-trip-allowance-btn").addEventListener("click", getTripAllowance);
    document.getElementById("exclude-days-btn").addEventListener("click", getTripDates);
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