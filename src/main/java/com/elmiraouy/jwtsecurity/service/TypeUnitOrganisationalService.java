public interface TypeUnitOrganisationalService {
    public TypeUnitOrganisationalResponseDto save(TypeUnitOrganisationalRequest typeUnitOrganisationalRequest)
            throws CompanyException, EntityNotFoundException, TypeUnityException;
    public TypeUnitOrganisationalResponseDto update(TypeUnitOrganisationalRequest typeUnitOrganisationalRequest)
            throws CompanyException, EntityNotFoundException, TypeUnityException;
    public TypeUnitOrganisationalResponseDto duplicate(TypeUnitOrganisationalRequest typeUnitOrganisationalRequest) throws CompanyException, EntityNotFoundException, TypeUnityException;
    public void delete(Long id);
    public TypeUnitOrganisationalResponseDto findById(Long id) throws EntityNotFoundException;
    public TypeUnitOrganisational findByIdIntern(Long id) throws EntityNotFoundException, TypeUnityException;
    public List<TypeUnitOrganisationalResponseDto> getAll(Long companyId) throws EntityNotFoundException;
    public TypeUnitOrganisationalResponseDto findByNameOrCode(String criteria) throws EntityNotFoundException;
    public List<TypeUnitOrganisationalResponseDto> findByCompanyId(Long companyId) throws EntityNotFoundException;

    void persistFromFile(MultipartFile file,String table,Long companyId,Long userCreatedId);
}
