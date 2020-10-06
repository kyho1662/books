const myUrl = 'http://localhost:8080';

const form = document.getElementById('form');
const loginId = document.getElementById('loginid');
const password = document.getElementById('password');


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

function checkRequired(inputArr) {
  inputArr.forEach(function (input) {
    if (input.value.trim() === '') {
      showError(input, `${getFieldName(input)}을(를) 입력해주세요`);
    } else {
      showSuccess(input);
    }
  });
}

function checkLength(input, min, max) {
  if (input.value.length < min) {
    showError(input, `${getFieldName(input)}은(는) ${min}자 이상이어야 합니다`);
  } else if (input.value.length > max) {
    showError(input, `${getFieldName(input)}은(는) ${max}자 이하이어야 합니다`);
  } else {
    showSuccess(input);
  }
}

function checkSuccess(input) {
  const formControl = input.parentElement;
  if (formControl.className === 'form-control success') {
    return Boolean(true);
  } else {
    return Boolean(false);
  }
}

function getFieldName(input) {
  const fieldName = input.parentElement.querySelector('label').innerText;
  return fieldName;
}

async function signIn() {
  try {
    let loginRequest = {
      loginId: '',
      password: '',
    };

    loginRequest.loginId = loginId.value;
    loginRequest.password = password.value;

    const res = await fetch(myUrl + '/api/auth', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(loginRequest),
    });

    if (res.status === 200) {

      const result = await res.text();

      sessionStorage.setItem("memberId", result);

      alert('로그인 완료');
      location.href = '/';
    } else {
      showError(loginId, ``);
      showError(password, `잘못된 아이디 또는 비밀번호입니다`);
    }

  } catch (err) {
    console.log(err.message);
  }
}

form.addEventListener('submit', function (e) {
  e.preventDefault();

  checkRequired([
    loginId,
    password,
  ]);

  checkLength(loginId, 3, 15);
  checkLength(password, 6, 25);

  if (
    checkSuccess(loginId) &&
    checkSuccess(password)) {
    signIn();
  }
});