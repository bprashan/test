properties([parameters([
    [$class: 'ChoiceParameter', choiceType: 'PT_RADIO', description: 'Select Gramine performance benchmarking execution mode - either baremetal or container', filterLength: 1, filterable: false, name: 'perf_config', randomName: 'choice-parameter-1365615791311511', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: ''], script: [classpath: [], sandbox: false, script: 
        '''return ["baremetal:selected","container"]'''
    ]]],
    [$class: 'DynamicReferenceParameter', choiceType: 'ET_FORMATTED_HTML', description: 'Select Gramine installation mode (applicable for bare-metal performance benchmarking)', name: 'build_gramine', omitValueField: false, randomName: 'choice-parameter-1365615792175213', referencedParameters: 'perf_config', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: 'return[\'error\']'], script: [classpath: [], sandbox: false, script: '''if (perf_config.equals("baremetal")) {
    return """
    <input type="radio" id="package" name="value" value="package" checked>
    <label for="package">package</label>
    <input type="radio" id="source" name="value" value="source">
    <label for="source">source</label>
    <img src="force/failed/load/image" style="display: none;" onerror=\'document.getElementById("package").closest(".form-group").style.display=""\'>
    <img src="force/failed/load/image" style="display: none;" onerror=\'document.getElementById("source").closest(".form-group").style.display=""\'>
    """
    } else {
    return """
    <label><input type="radio" id="package" name="value" value="package" > package</label>
    <label><input type="radio" id="source" name="value" value="source"> source</label>
    <img src="force/failed/load/image" style="display: none;" onerror=\'document.getElementById("package").closest(".form-group").style.display="none"\'>
    <img src="force/failed/load/image" style="display: none;" onerror=\'document.getElementById("source").closest(".form-group").style.display="none"\'>
    """}'''
    ]]],
    [$class: 'DynamicReferenceParameter', choiceType: 'ET_FORMATTED_HTML', description: 'Provide Gramine repo source commit id to checkout. By default, latest commit from master branch shall be cloned.(Applicable for Gramine source installation mode only)', name: 'gramine_repo_commit_id', omitValueField: false, randomName: 'choice-parameter-1365615793016865', referencedParameters: 'build_gramine', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: 'return[\'error\']'], script: [classpath: [], sandbox: false, 
    script: ''' if (build_gramine.equals("source")) {
    return """
    <input name="value" id="build_gramine"  value="">
    <img src="force/failed/load/image" style="display: none;" onerror=\'document.getElementById("build_gramine").closest(".form-group").style.display=""\'>
    """
    } else {
    return """
    <input name="value" id="build_gramine"  value="">
    <img src="force/failed/load/image" style="display: none;" onerror=\'document.getElementById("build_gramine").closest(".form-group").style.display="none"\'>
    """}'''
    ]]],
    [$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT', description: 'select the marker to execute the Gramine performance benchmarking', filterLength: 1, filterable: false, name: 'run', randomName: 'choice-parameter-1365615793846734', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: 'return[\'error\']'], script: [classpath: [], sandbox: false, script: 
    '''return["ov_perf_throughput:selected", "ov_perf_latency", "redis_perf", "tf_perf"]'''
    ]]],
    string(description: 'Provide specific performance test to execute', name: 'run_specific_perf_test', trim: true),
    [$class: 'CascadeChoiceParameter', choiceType: 'PT_CHECKBOX', filterLength: 1, filterable: false, name: 'exec_mode', randomName: 'choice-parameter-1365615795045728', referencedParameters: 'run', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: 'return[\'error\']'], script: [classpath: [], sandbox: false, 
    script: '''if (run.equals("redis_perf")) {
    return ["native:selected", "gramine-direct:selected", "gramine-sgx-single-thread-non-exitless:selected", "gramine-sgx-diff-core-exitless:selected"]} 
    else {
    return ["native:selected", "gramine-direct:selected", "gramine-sgx:selected"]}'''
    ]]],
    string(defaultValue: '5', description: 'the repetition of an entire performance benchmarking runs', name: 'iterations', trim: true)])
])