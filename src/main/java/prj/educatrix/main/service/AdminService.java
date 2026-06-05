package prj.educatrix.main.service;

import java.awt.print.Pageable;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;


import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import prj.educatrix.main.domain.*;

import prj.educatrix.main.dto.CategoryDTO;
import prj.educatrix.main.dto.TransactionDetailDTO;
import prj.educatrix.main.dto.UserDetailsDTO;
import prj.educatrix.main.repository.*;

@Service
public class AdminService {
    private final AccountRepository accountRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final CategoryRepository categoryRepository;
    private final AccountStatusHistoryRepository accountStatusHistoryRepository;
    private final TransactionRepository transactionRepository;
    private final CourseStatisticsRepository courseStatisticsRepository;
    private final ReviewRepository reviewRepository;
    private final ProfileRepository profileRepository;
    private final SocialLinkRepository socialLinkRepository;
    private final LessonRepository lessonRepository;
    private final FileRepository fileRepository;
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;

    public AdminService(AccountRepository accountRepository, CourseRepository courseRepository, RegistrationRepository registrationRepository, CategoryRepository categoryRepository, AccountStatusHistoryRepository accountStatusHistoryRepository, TransactionRepository transactionRepository, CourseStatisticsRepository courseStatisticsRepository, ReviewRepository reviewRepository, ProfileRepository profileRepository, SocialLinkRepository socialLinkRepository, LessonRepository lessonRepository, FileRepository fileRepository, OrderRepository orderRepository, NotificationRepository notificationRepository) {
        this.accountRepository = accountRepository;
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
        this.categoryRepository = categoryRepository;
        this.accountStatusHistoryRepository = accountStatusHistoryRepository;
        this.transactionRepository = transactionRepository;
        this.courseStatisticsRepository = courseStatisticsRepository;
        this.reviewRepository = reviewRepository;
        this.profileRepository = profileRepository;
        this.socialLinkRepository = socialLinkRepository;
        this.lessonRepository = lessonRepository;
        this.fileRepository = fileRepository;
        this.orderRepository = orderRepository;
        this.notificationRepository = notificationRepository;
    }

    public List<Account> getListUser() {
        return accountRepository.findAll();
    }
    @Transactional
    public void saveUser(Account account) {
        accountRepository.save(account);
    }
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }


    public List<Teacher_registration> getListTeacherRegistration() {

        return registrationRepository.findAll();
    }

    public Optional<Account> findById(String id) {

        return accountRepository.findById(id);
    }
    public List<Account> findByUsernameContainsAndRole(String username, String role_id) {

            return accountRepository.findByUsernameContainsAndRole_id(username, role_id);
    }
    @Transactional
    public List<Account> sortByUsername(String type,String typeRole) {
        List<Account> list = accountRepository.findByRole_RoleId(typeRole);
    if (type.equalsIgnoreCase("Sort A-Z")) {

        list.sort(Comparator.comparing(Account::getUsername));
    }else{
        list.sort(Comparator.comparing(Account::getUsername).reversed());
    }
        return list;
    }
    public void sortByCourseName(List<Course> courseList, String sortType) {
        if (sortType.equals("Sort A-Z")) {
            courseList.sort(Comparator.comparing(Course::getCourseName));
        } else if (sortType.equals("Sort Z-A")) {
            courseList.sort(Comparator.comparing(Course::getCourseName).reversed());
        }
    }


    public List<Account> filterByRole(String role) {
        List<Account> accountList = null;

        if (role.equalsIgnoreCase("Learner")) {
            accountList = accountRepository.findByRole_RoleId("Learner");
        }
        if(role.equalsIgnoreCase("Teacher")) {
            accountList = accountRepository.findByRole_RoleId("Teacher");
        }
        return accountList;
    }
    @Transactional
    public List<Course> filterByCategory(List<Course> courseList, Set<Integer> categorySet) {

        if (courseList == null || courseList.isEmpty() || categorySet == null || categorySet.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.println(categorySet);

        List<Course> filteredCourses = new ArrayList<>();

        for (Course course : courseList) {
            Set<Category> courseCategories = course.getCategories();
            if (courseCategories == null) {
                continue;
            }
            for (Category category : courseCategories) {
                if (categorySet.contains(category.getId())) {
                    filteredCourses.add(course);
                    break;
                }
            }
        }

        return filteredCourses;
    }


    public Teacher_registration findApplicationById(Integer id) {
        return registrationRepository.findById(id).isEmpty() ? null : registrationRepository.findById(id).get();
    }
    public List<Teacher_registration> findApplicationByStatus(String status) {
        return registrationRepository.findByStatus(status);
    }
    public List<Teacher_registration> findApplicationByStatusIn(List<String>status) {
        return registrationRepository.findTeacher_registrationByStatusIn(status);
    }
    public List<Teacher_registration> findApplicationByUsername(String username) {
        return registrationRepository.findTeacherRegistrationByAccountContaining(username);
    }

    public List<Account> filterByStatus(String status){
        return accountRepository.findByStatus(status);
    }
    @Transactional
    public List<Category> getListCategory() {
        return categoryRepository.getListCategory();
    }
    @Transactional
    public List<Course> getListCourse() {
        return courseRepository.findAllWithCategoriesAndLessonsAndFiles();
    }


    public void deleteByIdCourses(int id) {
        courseRepository.deleteById(id);
    }
    @Transactional
    public void deleteByIdAccount(String id) {
        registrationRepository.deleteByAccountId(id);

        accountRepository.deleteById(id);
    }
    @Transactional
    public void deleteAllByIdIn(List<String> ids) {
        accountRepository.deleteAllByIdIn(ids);
    }


    public List<AccountStatusHistory> getListAccountStatusHistory() {
        return accountStatusHistoryRepository.findAll();
    }
    public void saveAccountStatusHistory(AccountStatusHistory accountStatusHistory) {
        accountStatusHistoryRepository.save(accountStatusHistory);
    }
    public List<AccountStatusHistory> findAccountStatusHistoryByAccountId(String id) {
       return accountStatusHistoryRepository.findAccountStatusHistoryByAccountId(id);
    }
    @Transactional
    public List<Course> findCoursesByUsername(String username) {
        return courseRepository.findCoursesByCourseName(username);
    }

    @Transactional
    public List<Transaction> getListTransaction() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getFilteredInvoice(Map<String, Object> filters) {
        List<Transaction> transactionList = transactionRepository.findAll();
        System.out.println("Original Transactions: " + transactionList);

        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            String key = filter.getKey();
            String value = filter.getValue().toString();
            System.out.println("Filter applied -> " + key + ": " + value);

            switch (key) {
                case "sort_amount":
                    transactionList = sortByAmount(transactionList, value);
                    break;
                case "sort_date":
                    transactionList = sortByDate(transactionList, value);
                    break;
                case "payment":
                    transactionList = filterByMethod(transactionList, value);
                    break;
            }
        }
        return transactionList;
    }


    private List<Transaction> sortByAmount(List<Transaction> transactionList, String order) {
        return transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getOrderTotalAmount,
                        "amount_asc".equals(order) ? Comparator.naturalOrder() : Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private List<Transaction> sortByDate(List<Transaction> transactionList, String order) {
        return transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getTransactionDate,
                        "date_asc".equals(order) ? Comparator.naturalOrder() : Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
    public Optional<Course> findByCourseId(Integer courseId) {
        return courseRepository.findByIdWithLessonsAndCategories(courseId);
    }
    private List<Transaction> filterByMethod(List<Transaction> transactionList, String method) {
        if ("all".equalsIgnoreCase(method)) return transactionList;
        return transactionList.stream()
                .filter(app -> app.getMethodPayment().equalsIgnoreCase(method))
                .collect(Collectors.toList());
    }
    public List<Transaction> findTransactionsByTransactionID(String id) {
        return transactionRepository.findTransactionByTransactionIdContaining(id);
    }
    public List<Course_Statistics> findAllCourse_Statistics() {
        return courseStatisticsRepository.findAll();
    }
    public Optional<Course_Statistics> findCourse_StatisticsById(int id) {
        return courseStatisticsRepository.findById(id);
    }
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.getListCategory()
                .stream()
                .map(category -> {
                    int totalLearners = category.getCourses()
                            .stream()
                            .mapToInt(Course::getTotalEnrolled)
                            .sum();
                    return new CategoryDTO(totalLearners, (double) 0,0, category.getDescription(),category.getCategoryName(),category.getId());
                })
                .collect(Collectors.toList());
    }
    public Map<Integer, Long> getInvoicesPerMonth() {
        List<Object[]> results = transactionRepository.countInvoicesPerMonth();
        Map<Integer, Long> data = new HashMap<>();
        for (Object[] result : results) {
            data.put((Integer) result[0], (Long) result[1]); // {Tháng: Số lượng hóa đơn}
        }
        return data;
    }
    public Map<Integer, Double> sendRevenuePerMonth() {
        List<Object[]> results = transactionRepository.getMonthlyRevenue();
        Map<Integer, Double> data = new HashMap<>();
        for (Object[] result : results) {
            data.put((Integer) result[0], (Double) result[1]);
        }
        return data;
    }
    public Map<Double,Long> getCountRateOfUsers() {
        List<Object[]> results = reviewRepository.getRatingStatistics();
        Map<Double, Long> data = new HashMap<>();
        for (Object[] result : results) {
            data.put((Double) result[0], (Long) result[1]);
        }
        return data;
    }
    public Double getAverageCompletionRate() {
        return courseStatisticsRepository.getAverageCompletionRate();
    }
    public Double getAverageCompletionRateById(int id) {
        return courseStatisticsRepository.getAverageCompletionRateByCourseId(id);
    }

    public Double getAverageDropOutRate() {
        return courseStatisticsRepository.getAverageDropOutRate();
    }
    public List<Map<String, Object>> getTotalReviewsByCourse() {
        List<Object[]> results = reviewRepository.countReviewsByCourse();
        return results.stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put("course_name", row[0]);
            map.put("total_reviews", row[1]);
            return map;
        }).collect(Collectors.toList());
    }

    public Map<String, Object> getAvgRatingByCourse() {
        List<Object[]> results = reviewRepository.avgRatingByCourse();
        Map<String, Object> avgRatings = new HashMap<>();

        for (Object[] row : results) {
            avgRatings.put((String) row[0], row[1]);
        }

        return avgRatings;
    }

    public Map<String, Object> getRatingDistribution() {
        List<Object[]> results = reviewRepository.countRatingDistribution();
        Map<String, Object> ratingDistribution = new HashMap<>();

        for (Object[] row : results) {
            ratingDistribution.put(row[0].toString(), row[1]);
        }

        return ratingDistribution;
    } public Map<String, Object> getRatingDistributionCourse(int courseId) {
        List<Object[]> results = reviewRepository.countRatingDistributionCourse(courseId);
        Map<String, Object> ratingDistribution = new HashMap<>();

        for (Object[] row : results) {
            ratingDistribution.put(row[0].toString(), row[1]);
        }

        return ratingDistribution;
    }


    public Map<String, Object> getReviewsByCategory() {
        List<Object[]> results = reviewRepository.countReviewsByCategory();
        Map<String, Object> categoryReviews = new HashMap<>();

        for (Object[] row : results) {
            categoryReviews.put((String) row[0], row[1]);
        }

        return categoryReviews;
    }
    public void saveApplication(Teacher_registration teacher_registration) {
        registrationRepository.save(teacher_registration);
    }
    public UserDetailsDTO getUserDetails(String userId) {
        Optional<Account> accountOpt = accountRepository.findAccountWithDetailsById(userId);

        if (accountOpt.isPresent()) {
            return UserDetailsDTO.fromAccount(accountOpt.get());
        }

        return null;
    }
    public Optional<Account> findTeacher_registrationByAccount_Id(String accountId) {
        Optional<Account> accountOpt = accountRepository.findAccountWithDetailsById(accountId);
        return accountOpt;
    }
    public Teacher_registration getTeacher_registrationByAccount_Id(String accountId, String status) {
        return registrationRepository.findTeacherRegistrationByAccountIdAndStatus(accountId, status);
    }
    public List<Course> findCoursesByTeacher(String id) {
        return courseRepository.findCoursesByTeacher(id);
    }
    public List<Category> getListCategoryByCourse(int id) {
        return categoryRepository.getListCategoryByCourse(id);
    }
    public Profile getProfileById(String id) {
        return (Profile) profileRepository.findProfileByAccount_Id(id);
    }
    public List<Social_link> getListSocialLinkByAccount_Id(String id) {
        return socialLinkRepository.findAllByProfileId(id);
    }


    public List<Lesson> findByCourseIdOrderByPosition(Integer courseId) {
        return lessonRepository.findByCourseIdOrderByPosition(courseId);
    }

    @Transactional
    public Optional<Lesson> getLessonById(Integer id) {
        return lessonRepository.findByIdWithFiles(id);
    }
    public List<Reviews> findListLessonByCourseId(Integer courseId) {
        return reviewRepository.findByCourseId(courseId);
    }
    public int getTotalStudentCountByCourseId(Integer courseId) {
        return courseStatisticsRepository.getTotalStudentsCourse(courseId);
    }
    public int getTotalRevenueByCourseId(Integer courseId) {
        return courseStatisticsRepository.getTotalRevenueCourse(courseId);
    }

    @Transactional
    public void cancelTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        if ("PENDING".equalsIgnoreCase(transaction.getTransactionStatus())) {
            transaction.setTransactionStatus("FAIL");
            transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Cannot cancel transaction with status: " + transaction.getTransactionStatus());
        }
    }

    @Transactional(readOnly = true)
    public TransactionDetailDTO getTransactionDetail(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Ko tìm thấy: " + transactionId));

        Order order = transaction.getOrder();

        return TransactionDetailDTO.builder()
                .transactionId(transaction.getTransactionId())
                .methodPayment(transaction.getMethodPayment())
                .transactionDate(transaction.getTransactionDate())
                .transactionStatus(transaction.getTransactionStatus())
                .orderTotalAmount(order.getTotalAmount())
                .account(order.getAccount())
                .email(order.getEmail())
                .phoneNumber(order.getPhoneNumber())
                .courses(new ArrayList<>(order.getCourses()))
                .couponCode(order.getCouponCode())
                .build();
    }


    public List<Order> findAllOrder() {
        return orderRepository.findAllWithCoursesAndCategories();
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }
    public List<CategoryDTO> getAllCategoriesDTO() {
        List<Category> categories = categoryRepository.findAll();
        return convertToCategoryDTOList(categories);
    }

    public List<CategoryDTO> searchCategories(String keyword) {
        List<Category> categories = categoryRepository.searchByName(keyword);
        return convertToCategoryDTOList(categories);
    }

    public CategoryDTO saveCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return convertToCategoryDTO(savedCategory);
    }



    private List<CategoryDTO> convertToCategoryDTOList(List<Category> categories) {
        return categories.stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

 public List<CategoryDTO> getAllCategoriesWithStats() {
        List<Category> categories = categoryRepository.getListCategory();
        return categories.stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryDTO.getCategoryName())) {
            throw new IllegalArgumentException("Category with name '" + categoryDTO.getCategoryName() + "' already exists");
        }

        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());

        Category savedCategory = categoryRepository.save(category);
        return convertToCategoryDTO(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Optional<Category> existingCategoryOpt = categoryRepository.findById(categoryDTO.getId());
        if (existingCategoryOpt.isEmpty()) {
            throw new IllegalArgumentException("Category not found with id: " + categoryDTO.getId());
        }

        Category existingCategory = existingCategoryOpt.get();

        boolean duplicateExists = categoryRepository.findByCategoryNameIgnoreCase(categoryDTO.getCategoryName())
                .stream()
                .anyMatch(c -> !(c.getId() == categoryDTO.getId()));


        if (duplicateExists) {
            throw new IllegalArgumentException("Category with name '" + categoryDTO.getCategoryName() + "' already exists");
        }

        existingCategory.setCategoryName(categoryDTO.getCategoryName());
        existingCategory.setDescription(categoryDTO.getDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return convertToCategoryDTO(updatedCategory);
    }


    @Transactional
    public void deleteCategory(Integer id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isEmpty()) {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }

        Category category = categoryOpt.get();

        if (category.getCourses() != null && !category.getCourses().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete category that has associated courses");
        }

        categoryRepository.deleteById(id);
    }


    public List<CategoryDTO> filterCategories(List<String> filters) {
        List<Category> allCategories = categoryRepository.findAll();
        List<Category> filteredCategories = new ArrayList<>(allCategories);

        if (filters != null && !filters.isEmpty()) {
            String searchTerm = filters.stream()
                    .filter(f -> !f.startsWith("Sort"))
                    .findFirst()
                    .orElse("");

            if (!searchTerm.isEmpty()) {
                filteredCategories = filteredCategories.stream()
                        .filter(c -> c.getCategoryName().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
            }

            String sortOption = filters.stream()
                    .filter(f -> f.startsWith("Sort"))
                    .findFirst()
                    .orElse("");

            if (!sortOption.isEmpty()) {
                Comparator<Category> comparator = null;

                if (sortOption.contains("(A-Z)")) {
                    comparator = Comparator.comparing(Category::getCategoryName);
                } else if (sortOption.contains("(Z-A)")) {
                    comparator = Comparator.comparing(Category::getCategoryName).reversed();
                } else if (sortOption.contains("Course Count (High to Low)")) {
                    comparator = Comparator.comparing((Category c) -> c.getCourses() == null ? 0 : c.getCourses().size()).reversed();
                } else if (sortOption.contains("Course Count (Low to High)")) {
                    comparator = Comparator.comparing((Category c) -> c.getCourses() == null ? 0 : c.getCourses().size());
                }

                if (comparator != null) {
                    filteredCategories.sort(comparator);
                }
            }
        }

        return filteredCategories.stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }


    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescription(category.getDescription());

        int courseCount = category.getCourses() != null ? category.getCourses().size() : 0;
        dto.setCourseCount(courseCount);

        double totalRevenue = 0.0;
        if (category.getCourses() != null) {
            totalRevenue = category.getCourses().stream()
                    .mapToDouble(Course::getPrice)
                    .sum();
        }

        dto.setTotalRevenue(totalRevenue);

        return dto;
    }
}




