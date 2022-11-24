package lvd.excel;

public class InterfaceInfo {

    private String bizCode;

    private String abilityCode;

    private String interfaceCode;

    private String originalInterfaceCode;

    public InterfaceInfo() {
    }

    public InterfaceInfo(String bizCode, String abilityCode, String interfaceCode, String originalInterfaceCode) {
        this.bizCode = bizCode;
        this.abilityCode = abilityCode;
        this.interfaceCode = interfaceCode;
        this.originalInterfaceCode = originalInterfaceCode;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getAbilityCode() {
        return abilityCode;
    }

    public void setAbilityCode(String abilityCode) {
        this.abilityCode = abilityCode;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getOriginalInterfaceCode() {
        return originalInterfaceCode;
    }

    public void setOriginalInterfaceCode(String originalInterfaceCode) {
        this.originalInterfaceCode = originalInterfaceCode;
    }
}
