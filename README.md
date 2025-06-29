# **하이라이트 기능 기술 스펙 문서**
화면 구상 와이어 프레임
![Image](https://github.com/user-attachments/assets/4b7d700b-4253-4b85-a72b-2ad0816d90a6)

## **1. 하이라이트 생성 (파일 업로드)**

- `POST /highlights`
- 흐름도
    
    ```mermaid
    sequenceDiagram
    
    participant Client as 클라이언
    participant Server as 서버
    participant S3 as S3 Storage
    participant CDN as CDN
    
    Client->>Server: POST /highlights (파일 업로드)
    Server->>S3: 파일 업로드
    S3-->>Server: 업로드 완료
    Server-->>Client: 응답 (CDN 링크 포함)
    
    Client->>CDN: GET CDN 링크로 파일 요청
    CDN-->>Client: 파일 응답
    ```
    
- **요청 (Request)**
    - **Content-Type**: `multipart/form-data`
    - **요청 파트 (Request Parts)**
        - **`file`**:
            - **타입**: `MultipartFile` (파일 자체)
            - **필수 여부**: 필수
        - **`body`**:
            - **타입**: `CreateHighlightRequest` (객체)
            - **필수 여부**: optional
            - **예시**:JSON
                
                ```json
                {
                    "title": "책 제목",
                    "author": "작가 이름",
                    "page": 3
                }
                ```
                
- **응답 (Response)**
    - **설명**: 생성된 하이라이트의 고유 ID 및 CDN 접근 링크를 포함합니다.
    - **HTTP 상태 코드**: `200 OK` (성공 시)
    - **예시**:JSON
    
    ```json
    {
        "id": 1,
        "link": "https://cdn.example.com/highlights/a1b2c3d4e5f6.png",
        "title": null,
        "author": null,
        "page": null
    }
    ```
    

## **2. 하이라이트 정보 수정**

- `PUT /highlights/{id}`
- **설명**: 특정 하이라이트의 메타데이터를 수정합니다. 파일을 다른 파일로 변경 가능
- **요청 (Request)**
    - **Path Parameter**:
        - **`id`**:`Int`
            - **필수 여부**: 필수
    - **Request Body**:
        - `file: RequestPart`
            - **필수 여부**: optional
        - `UpdateHighlightRequest`
            - **필수 여부**: optional
- **응답 (Response)**
    - **설명**: 수정된 하이라이트의 정보를 반환합니다.
    - **HTTP 상태 코드**: `200 OK` (성공 시)

## **3. 특정 하이라이트 조회**

- `GET /highlights/{id}`
- **요청 (Request)**
    - **Path Parameter**:
        - **`id`**:
            - **타입**: `String`
            - **설명**: 조회할 하이라이트의 고유 ID
            - **필수 여부**: 필수
- **응답 (Response)**
    - **설명**: 조회된 하이라이트의 모든 정보를 반환합니다. 여기에는 하이라이트 파일에 접근할 수 있는 CDN 링크도 포함됩니다.
    - **HTTP 상태 코드**: `200 OK` (성공 시)

## **4. 전체 하이라이트 목록 조회 (나의 하이라이트)**
- `GET /me/highlights`
- 현재 인증된 사용자(나)의 모든 하이라이트 목록을 조회합니다.
- **요청 (Request)**
    - **요청 파라미터**: 없음 (인증 정보를 통해 사용자 식별)
- **응답 (Response)**
    - **타입**: `GetAllHighlightsResponse` (객체)
    - **설명**: 현재 사용자의 하이라이트 목록을 배열 형태로 반환합니다. 각 하이라이트 객체는 간략한 정보와 CDN 링크를 포함할 수 있습니다.
    - **HTTP 상태 코드**: `200 OK` (성공 시)
    - 응답값 예시
        
        ```json
        [
              {
                    "id": 1,
                    "link": "https://cdn.example.com/highlights/a1b2c3d4e5f6.jpg",
                    "title": null,
                    "author": null,
                    "page": null
                },
                {
                    "id": 2,
                    "link": "https://cdn.example.com/highlights/g7h8i9j0k1l2.jpg",
                    "title": "책 제목",
                    "author": "작가 이름",
                    "page": 3
              }
        ]
        ```
