import { useEffect, useState } from 'react';
import axios from 'axios';

const AddressSearch = () => {
  const [address, setAddress] = useState('');
  const [추가주소, set추가주소] = useState('');
  const [최종주소, set최종주소] = useState('');

  //백엔드 api url 주소를 /api/addUser로 Rest ful 연결
  //Restful 연결 : 자바Controller로 연결해서 db에 값 넣음
  //1. fetch then catch버전 async await x
  const saveFetch = () => {
    fetch('http://localhost:8080/api/addUser', {
        method:"post",
        headers:{
            'Content-Type':'application-json'
        },

        //java에서 파라미터값도 address로 설정해야함
        body:JSON.stringify({address:최종주소}),
    })
    //데이터 넣기 성공했을 때 보여줄 것
    .then(response => response.json())
  }

  //2. axios then catch 버전 async await x
  const saveAxios = () => {
    axios.post('http://localhost:8080/api/addUser',
        {address:최종주소})
    //데이터 넣기 성공했을 때 보여줄 것
    .then(response => alert('데이터 넣기 성공'))
  }

  const handleComplete = (data) => {
    let fullAddress = data.address;
    let extraAddress = '';

    //R : Road 도로명주소 J : Jibeon 지번주소
    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }

    setAddress(fullAddress);
  };

  const openPostcode = () => {
    new window.daum.Postcode({
        //oncomplete : 사용자가 주소 검색 완료했을 때 호출하는 함수 지정
        //호출하는 함수 : 주소 검색 완효 후 실행할 기능 선택
        //oncomplete - 다음에서 제공
      oncomplete: handleComplete,
    }).open();
  };

  //useEffect 활용해서 최종주소 추가
  useEffect(() => {
    set최종주소(`${address} ${추가주소}`);
  },[address,추가주소])
  return (
    <div>
      <button onClick={openPostcode}>주소 검색</button>
      {address && (
      <div>
        <input type='text' 
        placeholder='추가주소입력'
        value={추가주소}
        onChange={(e) => set추가주소(e.target.value)}
        />
      <div>선택한 주소: {address}</div>
      </div>
      )}

      {address && 추가주소 && (
        <>
        <p>최종주소</p>
        <h5>{최종주소}</h5>

        {/*
        나중에 value값으로 최종주소 db에 넣어야 할때
        안보이게
        */}
         <input type='hidden' value={최종주소}/>
        
        <p>{`${address} ${추가주소}`}</p>
        </>
      )}
    </div>
  );
};

export default AddressSearch;