package Semantic.SemanticNode;

import Type.BaseType;

import java.util.Vector;

public class SemanticParameterNode extends SemanticNode {
    private Vector<BaseType> parameter_type_list;
    private Vector<String> parameter_name_list;

    public SemanticParameterNode(Vector<BaseType> parameter_type_list, Vector<String> parameter_name_list) {
        this.parameter_type_list = parameter_type_list;
        this.parameter_name_list = parameter_name_list;
        this.leaf_value = false;
    }

    @Override
    public BaseType getType() {
        return null;
    }

    public Vector<BaseType> getParameterTypeList() {
        return parameter_type_list;
    }

    public Vector<String> getParameterNameList() {
        return parameter_name_list;
    }
}