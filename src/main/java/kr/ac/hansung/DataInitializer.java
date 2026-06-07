package kr.ac.hansung;

import kr.ac.hansung.entity.Product;
import kr.ac.hansung.entity.Role;
import kr.ac.hansung.entity.User;
import kr.ac.hansung.repository.ProductRepository;
import kr.ac.hansung.repository.RoleRepository;
import kr.ac.hansung.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
            .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        User admin = userRepository.findByEmail("admin@hansung.ac.kr")
            .orElseGet(User::new);

        admin.setEmail("admin@hansung.ac.kr");
        admin.setPassword(passwordEncoder.encode("admin1234"));

        if (!admin.getRoles().contains(userRole)) {
            admin.getRoles().add(userRole);
        }

        if (!admin.getRoles().contains(adminRole)) {
            admin.getRoles().add(adminRole);
        }

        userRepository.save(admin);
        log.info("관리자 계정 초기화: admin@hansung.ac.kr / admin1234");

        if (productRepository.count() == 0) {
            productRepository.save(new Product("Spring Boot 4 완벽 가이드", 35000, "Spring Boot 4 + JPA + Security 실습서", 50));
            productRepository.save(new Product("Spring Security 7 핵심 원리", 28000, "세션·JWT·OAuth2 기반 보안 구현", 30));
            productRepository.save(new Product("JPA 프로그래밍 실전", 32000, "Hibernate 7 기반 ORM 마스터", 25));
            productRepository.save(new Product("Thymeleaf 완전 정복", 22000, "서버사이드 템플릿 엔진 가이드", 40));
            productRepository.save(new Product("MySQL 데이터베이스 입문", 26000, "MySQL 기본 문법과 실습", 35));
            productRepository.save(new Product("Docker 컨테이너 실습", 30000, "Docker Compose 기반 개발 환경 구성", 20));
            productRepository.save(new Product("Java 21 프로그래밍", 33000, "최신 Java 문법과 객체지향 설계", 45));
            productRepository.save(new Product("웹 프레임워크 실전", 29000, "Spring MVC 기반 웹 애플리케이션 개발", 28));
            productRepository.save(new Product("Bootstrap UI 디자인", 21000, "Bootstrap 5 기반 반응형 화면 구성", 60));
            productRepository.save(new Product("REST API 설계", 31000, "RESTful API 설계와 테스트", 18));
            productRepository.save(new Product("Hibernate ORM 핵심", 34000, "엔티티 매핑과 영속성 컨텍스트 이해", 22));
            productRepository.save(new Product("Spring Validation", 24000, "입력값 검증과 오류 처리", 33));
            productRepository.save(new Product("Git GitHub 협업", 20000, "버전 관리와 커밋 이력 관리", 55));
            productRepository.save(new Product("웹 보안 기초", 27000, "인증과 인가의 기본 개념", 31));
            productRepository.save(new Product("서비스 계층 설계", 25000, "Controller-Service-Repository 구조 이해", 26));
            productRepository.save(new Product("JPA 쿼리 메서드", 23000, "Repository 쿼리 작성 실습", 37));
            productRepository.save(new Product("Thymeleaf Form 처리", 22000, "폼 바인딩과 화면 처리", 42));
            productRepository.save(new Product("Spring Boot 테스트", 28000, "JUnit과 MockMvc 테스트 작성", 16));
            productRepository.save(new Product("관리자 페이지 구현", 32000, "ROLE_ADMIN 기반 관리 기능", 24));
            productRepository.save(new Product("상품 관리 프로젝트", 36000, "상품 등록, 조회, 수정, 삭제 종합 실습", 12));

            log.info("샘플 상품 20건 생성 완료");
        }
    }
}