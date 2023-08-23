// VARIABLES

let actualDailyAllowance = document.getElementById("actual-daily-allowance");
let actualDailyMileage = document.getElementById("actual-daily-mileage");
let actualDistanceLimit = document.getElementById("actual-limit-distance")
let actualLimitTotal = document.getElementById("actual-limit-total");


// URLs

const url1 = 'http://localhost:8080/api/reimbursement/admin/allowance/daily';
const url2 = 'http://localhost:8080/api/reimbursement/admin/mileage/daily';
const url3 = 'http://localhost:8080/api/reimbursement/admin/limit/distance';
const url4 = 'http://localhost:8080/api/reimbursement/admin/limit/total';
const url5 = 'http://localhost:8080/api/reimbursement/admin/limit/type';
const url6 = 'http://localhost:8080/api/reimbursement/admin/type';

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

async function updateDailyAllowance() {
    try {
        const dailyAllowanceInput = document.getElementById("daily-allowance");
        const dailyAllowance = dailyAllowanceInput.value;

        const response = await fetch(url1, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: dailyAllowance
            }
        )
        getData(url1).then(data => {
            actualDailyAllowance.innerText = data;
        });
        console.log(response);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function updateDailyMileage() {
    try {
        const dailyMileageInput = document.getElementById("daily-mileage");
        const dailyMileage = dailyMileageInput.value;

        const response = await fetch(url2, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: dailyMileage
            }
        )
        getData(url2).then(data => {
            actualDailyMileage.innerText = data
        });

        console.log(response);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function updateLimitPerReceipt() {
    try {
        const receiptTypeInput = document.getElementById("receipt-types");
        const receiptType = receiptTypeInput.value;
        const limitByReceiptInput = document.getElementById("limit-by-receipt");
        const limitByReceipt = limitByReceiptInput.value;
        let actualLimitByReceipt = document.getElementById("actual-limit-by-receipt");

        const dataToSend = {
            "receiptType": receiptType,
            "limit": limitByReceipt
        };
        const response = await fetch(url5, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: JSON.stringify(dataToSend)
            }
        );
        if (await response.text() === "true") {
            actualLimitByReceipt.innerText = receiptType + ": " + limitByReceipt + "$";
        }

        console.log(response);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function updateDistanceLimit() {
    try {
        const distanceLimitInput = document.getElementById("limit-distance");
        const distanceLimit = distanceLimitInput.value;

        const response = await fetch(url3, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: distanceLimit
            }
        )
        getData(url3).then(data => {
            actualDistanceLimit.innerText = data
        });

        console.log(response);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function updateTotalLimit() {
    try {
        const totalLimitInput = document.getElementById("limit-total");
        const totalLimit = totalLimitInput.value;

        const response = await fetch(url4, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: totalLimit
            }
        )
        getData(url4).then(data => {
            actualLimitTotal.innerText = data
        });

        console.log(response);
    } catch (error) {
        console.error('Error:', error);
    }
}

function getReceiptTypes() {
    const dropdown = document.getElementById("receipt-types");
    let defaultOption = document.createElement('option');
    defaultOption.text = '-choose type-';
    dropdown.appendChild(defaultOption);

    getData(url6).then(d => {
        d.forEach(i => {
            const option = document.createElement("option");
            option.innerText = i.receiptType;
            option.value = i.receiptType;
            dropdown.appendChild(option);
        });
    });
}

async function updateReceiptTypeList() {
    try {
        const receiptTypeInput = document.getElementById("new-expense-type");
        const receiptType = receiptTypeInput.value;
        let recentlyAdded = document.getElementById("recently-added");

        const response = await fetch(url6, {
                method: 'POST',
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'
                },
                body: receiptType
            }
        );
        recentlyAdded.innerText = await response.text();
    } catch (error) {
        console.error('Error:', error);
    }
}

function initializeApp() {

    let actualDailyAllowance = document.getElementById("actual-daily-allowance");
    let actualDailyMileage = document.getElementById("actual-daily-mileage");
    let actualDistanceLimit = document.getElementById("actual-limit-distance")
    let actualLimitTotal = document.getElementById("actual-limit-total");


    document.getElementById("update-allowance-btn").addEventListener("click", updateDailyAllowance);
    document.getElementById("update-mileage-btn").addEventListener("click", updateDailyMileage);
    document.getElementById("add-expense-type-btn").addEventListener("click", updateReceiptTypeList);
    document.getElementById("update-limit-per-receipt-btn").addEventListener("click", updateLimitPerReceipt);
    document.getElementById("update-distance-limit-btn").addEventListener("click", updateDistanceLimit);
    document.getElementById("update-total-limit-btn").addEventListener("click", updateTotalLimit);


    getData(url1).then(data => {
        actualDailyAllowance.innerText = data;
    });
    getData(url2).then(data => {
        actualDailyMileage.innerText = data;
    });
    getData(url3).then(data => {
        actualDistanceLimit.innerText = data;
    });
    getData(url4).then(data => {
        actualLimitTotal.innerText = data;
    });

    getReceiptTypes();

}

// CALLING FUNCTIONS
window.addEventListener("DOMContentLoaded", initializeApp);
