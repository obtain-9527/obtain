## **spring Security oauth 2**

1.springSecurity的基础：

​		UserDetails->UserDetailsService(自定义认证使用)->Authoritication；

​		UserDetailsService中添加权限如果使用Role需要添加（ROLE_开头）

```java
ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));//认证使用hasRole("ANDMIN")
authorities.add(new SimpleGrantedAuthority("read"));//认证使用hasAuthority("read")
```

​	简单实现OAuth:

```java
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //存储使用jwt
    @Autowired
    private TokenStore jwtTokenStore;
	
    /**
     * JwT解析工具
     */
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * userDetailsService
     */
    @Autowired
    private UserDetailsService userDetailsService;

    //加密协议
    @Bean
    public PasswordEncoder getPasswordEncoder(){return new BCryptPasswordEncoder();}
    /**
     * 客服端认证
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("obtain") //客服端
                .secret("123456")	//密钥
                .scopes("app")		//s使用范围
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")//认证方式
                .accessTokenValiditySeconds(3 * 60 * 60)
                .refreshTokenValiditySeconds(6 * 60 * 60);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(jwtTokenStore) //设置jwtToken为tokenStore
                .accessTokenConverter(jwtAccessTokenConverter);//设置access_token转换器
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")//开放/oauth/token_key Api
                .checkTokenAccess("permitAll()")//开放/oauth/check_token Api
                .allowFormAuthenticationForClients();//开启post表单提交
    }

    /**
     * 默认令牌服务
     * @return
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();//new 除象
        defaultTokenServices.setTokenStore(jwtTokenStore);//设置jwt为存储单位
        defaultTokenServices.setSupportRefreshToken(true);//设置可以刷新
        return defaultTokenServices;
    }
    
    /**
     * Jwt密钥
     */
    private String secrecy = "yj.obtain.com";

    @Bean("jwtTokenStore")
    public TokenStore getTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwt访问token转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(secrecy); //资源服务器需要配置此选项方能解密jwt的token
        return converter;
    }
}
```

最后添加webSecurityConfigurationAdapter

```java
JwtAccessTokenConverter@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){ //主要是配置这个Bean，用于授权服务器配置中注入
        return new MyUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().and()
            .csrf().disable();
    }
}
```

客服端认证时如果使用JWTTokenStory必须配置JwtAccessTokenConverter

```java
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private String secrecy = "yj.obtain.com";
    //http的路由权限
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/map").hasAuthority("read");

    }

    //配置之源服务器的策略
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore());
    }

    /**
     * jwt令牌存储器
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 认证服务器必须配置该项切必须与认证服务器的相同->客服端解密
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(secrecy);
        return converter;
    }
}
```