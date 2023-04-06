const GET_MEMBER_DATA = "http://34.100.205.140/get-member-data";
const GET_CLAIM_DATA = "http://34.100.205.140/get-claim-data";

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
  console.log(data);
  text += `<span>Member Name:</span> <strong>${data.memberName}</strong><br>
  <span>Member ID:</span> <strong>${data.memberID}</strong><br>
  <span>Insurer Name:</span> <strong>${data.insurer}</strong><br>
  <span>Email:</span> <strong>${data.email}</strong><br>
  <span>Mobile:</span> <strong>${data.mobile}</strong><br>`;

  document.getElementById("member-detail").innerHTML = text;
}

claimData.then((data) => {
  showClaimDate(data);
});
memberData.then((data) => {
  showMemberData(data);
});
