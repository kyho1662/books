const myUrl = 'http://localhost:8080';

const form = document.getElementById('form');
const loginId = document.getElementById('loginid');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');
const username = document.getElementById('username');
const email = document.getElementById('email');
const address = document.getElementById('address');
const phone = document.getElementById('phone');

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

// 입력 길이 체크
function checkLength(input, min, max) {
  if (input.value.length < min) {
    showError(input, `${getFieldName(input)}은(는) ${min}자 이상이어야 합니다`);
  } else if (input.value.length > max) {
    showError(input, `${getFieldName(input)}은(는) ${max}자 이하이어야 합니다`);
  } else {
    showSuccess(input);
  }
}

// 비밀번호 확인 체크
function checkPasswordMatch(input1, input2) {
  if (input1.value !== input2.value) {
    showError(input2, '비밀번호를 확인해주세요');
  }
}

// 아이디 중복 체크
async function checkDuplication(input) {
  try {

    if (input.value.length < 3 || input.value.length > 15) {
        checkLength(input, 3, 15);
        return;
    }

    const res = await fetch(myUrl + `/api/idCheck/${input.value}`);

    console.log(res.status);
    if (res.status === 200) {
      showSuccess(input);
    } else {
      showError(input, `중복된 ${getFieldName(input)}입니다`);
    }
  } catch (err) {
    console.log(err.message);
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

// ajax post
async function signUp() {
  try {

    let member = {
      loginId: '',
      password: '',
      name: '',
      email: '',
      address: '',
      phone: '',
    };

    member.loginId = loginId.value;
    member.password = password.value;
    member.name = username.value;
    member.email = email.value;
    member.address = address.value;
    member.phone = phone.value;

    const res = await fetch(myUrl + '/api/member', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(member),
    });

    if (res.status === 200) {
      const result = await res.text();

      console.log(result.message);
      alert('회원가입 완료');
      location.href = '/';
    }
  } catch (err) {
    console.log(err.message);
  }
}

// 필드명 가져오기
function getFieldName(input) {
  const fieldName = input.parentElement.querySelector('label').innerText;
  return fieldName;
}

// Event listeners
form.addEventListener('submit', function (e) {
  e.preventDefault();

  checkRequired([
    loginId,
    password,
    password2,
    username,
    email,
    address,
    phone,
  ]);

  checkLength(loginId, 3, 15);
  checkLength(password, 6, 25);
  checkLength(password2, 6, 25);
  checkEmail(email);
  checkPasswordMatch(password, password2);
  checkDuplication(loginId);

  if (
    checkSuccess(loginId) &&
    checkSuccess(password) &&
    checkSuccess(password2) &&
    checkSuccess(username) &&
    checkSuccess(email) &&
    checkSuccess(address) &&
    checkSuccess(phone)
  ) {
    signUp();
  }
});
