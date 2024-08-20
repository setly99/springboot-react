import React, {useState, useEffect} from 'react';
import axios from 'axios';
import './AirPollutionData.css';

const AirPollutionData = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error ,setError] = useState(null);

    //axios 이용한 api 연결 useEffect 사용
    useEffect(() => {
        // API 호출
        axios.get('http://localhost:8081/api/air-pollution')
            .then((response) => {
                const parser = new DOMParser();
                //공공데이터는 xml형식이기 때문에 xml형식으로 변경
                const xml = parser.parseFromString(response.data, "text/xml");
                const items = xml.getElementsByTagName("item");

                //공기오염 데이터 가져오기
                const parsedData = Array.from(items).map(item => {
                    const fullDateTime = item.getElementsByTagName("dataTime")[0]?.textContent;
                    const yearMonth = fullDateTime ? fullDateTime.slice(0, 7) : 'N/A';

                    return {
                        dataTime: yearMonth,//
                        stationName: item.getElementsByTagName("stationName")[0]?.textContent,
                        so2Grade: item.getElementsByTagName("so2Grade")[0]?.textContent,
                        coFlag: item.getElementsByTagName("coFlag")[0]?.textContent,//
                        khaiValue: item.getElementsByTagName("khaiValue")[0]?.textContent,
                        pm10Value: item.getElementsByTagName("pm10Value")[0]?.textContent,
                        no2Value: item.getElementsByTagName("no2Value")[0]?.textContent,
                        o3Value: item.getElementsByTagName("o3Value")[0]?.textContent
                    };
                });

                setData(parsedData);
                setLoading(false);
            })
            .catch((error) => {
                setError(error);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }
        
    
    return(
        <>
        <h1>공공 공기 데이터 조회</h1>
        <div className='card-container'>
            {/*data들을 가져와서 item으로 하나씩 꺼내서 데이터 보기 */}
            {data.map((item, index) => (
                <div key={index} className='card'>
                    <h2 className='stationName'>{item.stationName}</h2>
                    <p><strong>년/월</strong>{item.dataTime}</p>
                    <p><strong>SO2등급 : </strong>{item.so2Grade}</p>
                    <p><strong>CO상태 : </strong>{item.coFlag}</p>
                    <p><strong>종합대기질지수 : </strong>{item.khaiValue}</p>
                    <p><strong>PM10농도 : </strong>{item.pm10Value}</p>
                    <p><strong>NO2농도 : </strong>{item.no2Value}</p>
                    <p><strong>03농도 : </strong>{item.o3Value}</p>
                </div>
            ))}
        </div>
        </>
    );
};
export default AirPollutionData;