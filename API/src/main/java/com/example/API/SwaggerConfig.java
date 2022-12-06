@Configuration
public class SwaggerConfiguration {
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Demo")
                .description("Demo application API")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .paths(PathSelectors.any())
                .build();
    }
}
