package wsproposta.proposta.datamodel.REST;

import lombok.Getter;

public class OrganizacaoRestDTO {

    @Getter
    int nifOrganizacao;
    @Getter
    String denominacao;

    public OrganizacaoRestDTO() {
    }

    public OrganizacaoRestDTO(int nifOrganizacao, String denominacao) {
        this.nifOrganizacao = nifOrganizacao;
        this.denominacao = denominacao;
    }
}
