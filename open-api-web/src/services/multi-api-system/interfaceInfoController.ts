// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /interfaceInfo/add */
export async function addInterfaceInfo(
  body: API.InterfaceInfoAddDo,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityLong>('/interfaceInfo/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /interfaceInfo/delete */
export async function deleteInterfaceInfo(body: API.IdDto, options?: { [key: string]: any }) {
  return request<API.ResponseEntityBoolean>('/interfaceInfo/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /interfaceInfo/get */
export async function getInterfaceInfoById(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getInterfaceInfoByIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityInterfaceInfoDo>('/interfaceInfo/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /interfaceInfo/invoke */
export async function invokeInterfaceInfo(
  body: API.InterfaceInfoInvokeDto,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityObject>('/interfaceInfo/invoke', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /interfaceInfo/list */
export async function listInterfaceInfo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listInterfaceInfoParams,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityListInterfaceInfoDo>('/interfaceInfo/list', {
    method: 'GET',
    params: {
      ...params,
      interfaceInfoQueryDto: undefined,
      ...params['interfaceInfoQueryDto'],
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /interfaceInfo/list/page */
export async function listInterfaceInfoByPage(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listInterfaceInfoByPageParams,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityPageInterfaceInfoDo>('/interfaceInfo/list/page', {
    method: 'GET',
    params: {
      ...params,
      interfaceInfoQueryPageDto: undefined,
      ...params['interfaceInfoQueryPageDto'],
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /interfaceInfo/offline */
export async function offlineInterfaceInfo(body: API.IdDto, options?: { [key: string]: any }) {
  return request<API.ResponseEntityBoolean>('/interfaceInfo/offline', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /interfaceInfo/online */
export async function onlineInterfaceInfo(body: API.IdDto, options?: { [key: string]: any }) {
  return request<API.ResponseEntityBoolean>('/interfaceInfo/online', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /interfaceInfo/update */
export async function updateInterfaceInfo(
  body: API.InterfaceInfoUpdateDo,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityBoolean>('/interfaceInfo/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
