const myUrl = 'http://localhost:8080';

const form = document.getElementById('form');
const loginId = document.getElementById('loginid');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');
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

function checkLength(input, min, max) {
  if (input.value.length < min) {
    showError(input, `${getFieldName(input)}은(는) ${min}자 이상이어야 합니다`);
  } else if (input.value.length > max) {
    showError(input, `${getFieldName(input)}은(는) ${max}자 이하이어야 합니다`);
  } else {
    showSuccess(input);
  }
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

// 비밀번호 확인 체크
function checkPasswordMatch(input1, input2) {
  if (input1.value !== input2.value) {
    showError(input2, '비밀번호를 확인해주세요');
  }
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
async function resetPassword() {
  try {
    let resetPasswordRequest = {
      loginId: '',
      name: '',
      email: '',
      password: '',
    };
    resetPasswordRequest.loginId = loginId.value;
    resetPasswordRequest.name = username.value;
    resetPasswordRequest.email = email.value;
    resetPasswordRequest.password = password.value;

    const res = await fetch(myUrl + '/api/member/resetPassword', {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(resetPasswordRequest),
    });

    const result = await res.text();

     if (res.status === 200) {
          showSuccess(loginId);
          showSuccess(username);
          showSuccess(email);
          showSuccess(password);
          showSuccess(password2);

          alert(`비밀번호가 변경되었습니다`);

          location.href = '/';
        } else {
          showError(loginId, ``);
          showError(username, ``);
          showError(email, `회원을 찾을 수 없습니다`);
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
    password2,
    username,
    email,
  ]);

  checkLength(loginId, 3, 15);
  checkLength(password, 6, 25);
  checkLength(password2, 6, 25);
  checkPasswordMatch(password, password2);
  checkEmail(email);

  if (
    checkSuccess(loginId) &&
    checkSuccess(password) &&
    checkSuccess(password2) &&
    checkSuccess(username) &&
    checkSuccess(email)
  ) {
    resetPassword();
  }
});