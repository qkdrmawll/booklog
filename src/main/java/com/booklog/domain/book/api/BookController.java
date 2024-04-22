package com.booklog.domain.book.api;

import com.booklog.domain.book.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Value("${kakao.key}")
    private String key;
    private String url = "https://dapi.kakao.com/v3/search/book";
    @GetMapping("/kakao/book")
    public String showSearchPage() {
        return "book";
    }
    @GetMapping("/kakao/book/search")
    public String callApi(@RequestParam String query, Model model){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key); //Authorization 설정
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders); //엔티티로 만들기
        URI targetUrl = UriComponentsBuilder
                .fromUriString(url) //기본 url
                .queryParam("query", query) //인자
                .build()
                .encode(StandardCharsets.UTF_8) //인코딩
                .toUri();

        //GetForObject는 헤더를 정의할 수 없음
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        System.out.println(result);


        // ResponseEntity<Map>에서 'documents' 키를 통해 List<Map>을 가져옵니다.
        List<Map<String, Object>> documents = (List<Map<String, Object>>) result.getBody().get("documents");

        List<Book> books = new ArrayList<>();

        // documents의 각 요소에 대해 반복합니다.
        for (Map<String, Object> document : documents) {
            Book book = Book.builder()
                    .title((String) document.get("title"))
                    .authors((List<String>) document.get("authors"))
                    .contents((String) document.get("contents"))
                    .publisher((String) document.get("publisher"))
                    .price((int) document.get("price"))
                    .thumbnail((String) document.get("thumbnail"))
                    .build();
            books.add(book);
        }

        model.addAttribute("books", books);
        return "book";
    }
}
