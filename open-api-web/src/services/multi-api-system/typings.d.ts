declare namespace API {
  type getInterfaceInfoByIdParams = {
    id: number;
  };

  type getMailCaptchaParams = {
    email: string;
  };

  type getUserInterfaceInfoByIdParams = {
    id: number;
  };

  type IdDto = {
    id?: number;
  };

  type InterfaceInfoAddDo = {
    name?: string;
    description?: string;
    url?: string;
    requestParams?: string;
    requestHeader?: string;
    responseHeader?: string;
    method?: string;
  };

  type InterfaceInfoDo = {
    id?: number;
    name?: string;
    description?: string;
    url?: string;
    requestParams?: string;
    requestHeader?: string;
    responseHeader?: string;
    status?: number;
    method?: string;
    userId?: number;
    createTime?: string;
    updateTime?: string;
    deleted?: number;
  };

  type InterfaceInfoInvokeDto = {
    id?: number;
    userRequestParams?: string;
  };

  type InterfaceInfoQueryDto = {
    id?: number;
    name?: string;
    description?: string;
    url?: string;
    requestHeader?: string;
    responseHeader?: string;
    status?: number;
    method?: string;
    userId?: number;
  };

  type InterfaceInfoQueryPageDto = {
    current?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    id?: number;
    name?: string;
    description?: string;
    url?: string;
    requestHeader?: string;
    responseHeader?: string;
    status?: number;
    method?: string;
    userId?: number;
  };

  type InterfaceInfoUpdateDo = {
    id?: number;
    name?: string;
    description?: string;
    url?: string;
    requestParams?: string;
    requestHeader?: string;
    responseHeader?: string;
    status?: number;
    method?: string;
  };

  type InterfaceInfoVO = {
    id?: number;
    name?: string;
    description?: string;
    url?: string;
    requestParams?: string;
    requestHeader?: string;
    responseHeader?: string;
    status?: number;
    method?: string;
    userId?: number;
    createTime?: string;
    updateTime?: string;
    deleted?: number;
    totalNum?: number;
  };

  type IPageUserVo = {
    size?: number;
    records?: UserVo[];
    current?: number;
    pages?: number;
    total?: number;
  };

  type listInterfaceInfoByPageParams = {
    interfaceInfoQueryPageDto: InterfaceInfoQueryPageDto;
  };

  type listInterfaceInfoParams = {
    interfaceInfoQueryDto: InterfaceInfoQueryDto;
  };

  type listUserInterfaceInfoByPageParams = {
    userInterfaceInfoQueryPageDto: UserInterfaceInfoQueryPageDto;
  };

  type listUserInterfaceInfoParams = {
    userInterfaceInfoQueryDto: UserInterfaceInfoQueryDto;
  };

  type OrderItem = {
    column?: string;
    asc?: boolean;
  };

  type PageDto = {
    current?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type PageInterfaceInfoDo = {
    records?: InterfaceInfoDo[];
    total?: number;
    size?: number;
    current?: number;
    orders?: OrderItem[];
    optimizeCountSql?: PageInterfaceInfoDo;
    searchCount?: PageInterfaceInfoDo;
    optimizeJoinOfCountSql?: boolean;
    maxLimit?: number;
    countId?: string;
    pages?: number;
  };

  type PageUserInterfaceInfoDo = {
    records?: UserInterfaceInfoDo[];
    total?: number;
    size?: number;
    current?: number;
    orders?: OrderItem[];
    optimizeCountSql?: PageUserInterfaceInfoDo;
    searchCount?: PageUserInterfaceInfoDo;
    optimizeJoinOfCountSql?: boolean;
    maxLimit?: number;
    countId?: string;
    pages?: number;
  };

  type ResponseEntityBoolean = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type ResponseEntityInteger = {
    code?: number;
    data?: number;
    message?: string;
  };

  type ResponseEntityInterfaceInfoDo = {
    code?: number;
    data?: InterfaceInfoDo;
    message?: string;
  };

  type ResponseEntityIPageUserVo = {
    code?: number;
    data?: IPageUserVo;
    message?: string;
  };

  type ResponseEntityListInterfaceInfoDo = {
    code?: number;
    data?: InterfaceInfoDo[];
    message?: string;
  };

  type ResponseEntityListInterfaceInfoVO = {
    code?: number;
    data?: InterfaceInfoVO[];
    message?: string;
  };

  type ResponseEntityListUserInterfaceInfoDo = {
    code?: number;
    data?: UserInterfaceInfoDo[];
    message?: string;
  };

  type ResponseEntityLong = {
    code?: number;
    data?: number;
    message?: string;
  };

  type ResponseEntityObject = {
    code?: number;
    data?: Record<string, any>;
    message?: string;
  };

  type ResponseEntityPageInterfaceInfoDo = {
    code?: number;
    data?: PageInterfaceInfoDo;
    message?: string;
  };

  type ResponseEntityPageUserInterfaceInfoDo = {
    code?: number;
    data?: PageUserInterfaceInfoDo;
    message?: string;
  };

  type ResponseEntityUserInterfaceInfoDo = {
    code?: number;
    data?: UserInterfaceInfoDo;
    message?: string;
  };

  type ResponseEntityUserVo = {
    code?: number;
    data?: UserVo;
    message?: string;
  };

  type searchUsersParams = {
    userDto: UserDto;
    pageDto: PageDto;
  };

  type UserDo = {
    id?: number;
    userName?: string;
    avatarUrl?: string;
    gender?: number;
    loginName?: string;
    loginPwd?: string;
    accessKey?: string;
    secretKey?: string;
    phone?: string;
    email?: string;
    status?: number;
    userRole?: number;
    createTime?: string;
    updateTime?: string;
    deleted?: number;
  };

  type UserDto = {
    id?: number;
    userName?: string;
    loginName?: string;
    avatarUrl?: string;
    gender?: number;
    phone?: string;
    email?: string;
    status?: number;
    userRole?: number;
    createTime?: string;
    deleted?: number;
  };

  type UserInterfaceInfoAddDto = {
    userId?: number;
    interfaceInfoId?: number;
    totalNum?: number;
    leftNum?: number;
  };

  type UserInterfaceInfoDo = {
    id?: number;
    userId?: number;
    interfaceInfoId?: number;
    totalNum?: number;
    leftNum?: number;
    status?: number;
    createTime?: string;
    updateTime?: string;
    deleted?: number;
  };

  type UserInterfaceInfoQueryDto = {
    id?: number;
    userId?: number;
    interfaceInfoId?: number;
    totalNum?: number;
    leftNum?: number;
    status?: number;
  };

  type UserInterfaceInfoQueryPageDto = {
    current?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    id?: number;
    userId?: number;
    interfaceInfoId?: number;
    totalNum?: number;
    leftNum?: number;
    status?: number;
  };

  type UserInterfaceInfoUpdateDto = {
    id?: number;
    totalNum?: number;
    leftNum?: number;
    status?: number;
  };

  type UserLoginDto = {
    loginName?: string;
    loginPwd?: string;
  };

  type UserRegisterDto = {
    loginName?: string;
    loginPwd?: string;
    checkPwd?: string;
    email?: string;
    captcha?: string;
  };

  type UserVo = {
    id?: number;
    userName?: string;
    loginName?: string;
    accessKey?: string;
    secretKey?: string;
    avatarUrl?: string;
    gender?: number;
    phone?: string;
    email?: string;
    status?: number;
    userRole?: number;
    createTime?: string;
    deleted?: number;
  };
}
