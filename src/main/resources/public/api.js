const GET_MEMBER_DATA = "http://34.174.80.77:8080/get-member-data";
const GET_CLAIM_DATA = "http://34.174.80.77:8080/get-claim-data";
const POST_DATA_URL = "http://34.174.80.77:8080/submit-vac-dtl";

async function getData(url) {
  const response = await fetch(url);

  var data = await response.json();
  return data;
}

const claimData = getData(GET_CLAIM_DATA);
const memberData = getData(GET_MEMBER_DATA);

function showClaimDate(data) {
  let tab = ``;

  for (let r of data) {
    tab += `<tr> 
        <td>${r.claimId} </td>
        <td>${r.claimStatus}</td>
        <td>${r.claimType}</td> 
        <td>${r.insurerName}</td>   
        <td>${r.memberId}</td>    
        <td>${r.memberName}</td>
        <td>${r.patientId}</td>   
    </tr>`;
  }
  document.getElementById("claim-table").innerHTML = tab;
}

function showMemberData(data) {
  let text = ``;
  let dependentData = ``;
  console.log(data);
  text += `<span>Member Name:</span> <strong>${data.memberName}</strong><br>
  <span>Member ID:</span> <strong>${data.memberID}</strong><br>
  <span>Insurer Name:</span> <strong>${data.insurer}</strong><br>
  <span>Email:</span> <strong>${data.email}</strong><br>
  <span>Mobile:</span> <strong>${data.mobile}</strong><br>`;

  dependentData += `<table>
  <thead>
    <th>Dependant ID</th>
    <th>Dependant Name</th>
    <th>Age</th>
    <th>Relation</th>
    <th>DOB</th>
  </thead>`;
  for (let r of data.dependents) {
    dependentData += `<tr> 
        <td>${r.depId} </td>
        <td>${r.dependentName}</td>
        <td>${r.age}</td> 
        <td>${r.relationship}</td>   
        <td>${r.dob}</td>    
    </tr>`;
  }
  dependentData += `</table>`;

  document.getElementById("member-detail").innerHTML = text;
  document.getElementById("dependent-data").innerHTML = dependentData;
}

claimData.then((data) => {
  showClaimDate(data);
});
memberData.then((data) => {
  showMemberData(data);
});

//FORM API

async function postData(e) {
  e.preventDefault();

  var memberId = document.getElementById("memberId").value;
  var vacName = document.getElementById("vacName").value;
  var date = document.getElementById("date").value;
  var amount = document.getElementById("amt").value;
  var providerName = document.getElementById("providerName").value;

  const data = {
    memberId: memberId,
    vacName: vacName,
    date: date,
    amount: amount,
    providerName: providerName,
  };
  await fetch(POST_DATA_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      if (response.ok) {
        document.getElementById("submit-success").style.display = "block";
      }
      return response.json();
    })
    .then((data) => {
      console.log("API response:", data);
    })
    .catch((error) => {
      console.error("Error posting data to API:", error);
    });
}
