const myUrl = 'http://localhost:8080';

const form = document.getElementById('form');
const username = document.getElementById('username');
const email = document.getElementById('email');

// 에러 메세지 표시
function showError(input, message) {
  const formControl = input.parentElement;
  formControl.className = 'form-control error';
  const small = formControl.querySelector('small');
  small.innerText = message;
}

// 외곽선 표시
function showSuccess(input) {
  const formControl = input.parentElement;
  formControl.className = 'form-control success';
}

// 이메일 체크
function checkEmail(input) {
  const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  if (re.test(input.value.trim())) {
    showSuccess(input);
  } else {
    showError(input, '잘못된 이메일 주소입니다');
  }
}

// 입력 항목 체크
function checkRequired(inputArr) {
  inputArr.forEach(function (input) {
    if (input.value.trim() === '') {
      showError(input, `${getFieldName(input)}을(를) 입력해주세요`);
    } else {
      showSuccess(input);
    }
  });
}

// 유효한 항목 체크
function checkSuccess(input) {
  const formControl = input.parentElement;
  if (formControl.className === 'form-control success') {
    return Boolean(true);
  } else {
    return Boolean(false);
  }
}

// 필드명 가져오기
function getFieldName(input) {
  const fieldName = input.parentElement.querySelector('label').innerText;
  return fieldName;
}

// ajax post
async function findId() {
  try {
    let findIdRequest = {
      name: '',
      email: '',
    };

    findIdRequest.name = username.value;
    findIdRequest.email = email.value;

    const res = await fetch(myUrl + '/api/member/findId', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(findIdRequest),
    });

    const result = await res.text();

    if (res.status === 200) {
      showSuccess(username);
      showSuccess(email);

      alert(`아이디는 ${result} 입니다`);

      location.href = '/';
    } else {
      showError(username, ``);
      showError(email, `아이디를 찾을 수 없습니다`);
    }

  } catch (err) {
    console.log(err.message);
  }
}


form.addEventListener('submit', function (e) {
  e.preventDefault();

  checkRequired([
    username,
    email,
  ]);


  checkEmail(email);
  if (

    checkSuccess(username) &&
    checkSuccess(email)
  ) {
    findId();
  }
});