package wsproposta.proposta.datamodel.REST;

import lombok.Getter;

public class OrganizacaoRestDTO {

    @Getter
    long nr;
    @Getter
    String name;

    public OrganizacaoRestDTO() {
    }

    public OrganizacaoRestDTO(long nifOrganizacao, String denominacao) {
        this.nr = nifOrganizacao;
        this.name = denominacao;
    }
}
